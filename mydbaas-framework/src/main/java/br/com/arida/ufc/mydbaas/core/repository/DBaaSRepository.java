package main.java.br.com.arida.ufc.mydbaas.core.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import main.java.br.com.arida.ufc.mydbaas.common.resource.DBaaS;
import main.java.br.com.arida.ufc.mydbaas.core.repository.common.GenericRepository;
import main.java.br.com.arida.ufc.mydbaas.core.repository.connection.ConnectionPool;
import main.java.br.com.arida.ufc.mydbaas.util.DataUtil;
import br.com.caelum.vraptor.ioc.Component;

/**
 * @author David Araújo - @araujodavid
 * @version 3.0
 * @since March 18, 2013 
 */
@Component
public class DBaaSRepository implements GenericRepository<DBaaS> {

	private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
	
	@Override
	public List<DBaaS> all() {
		ArrayList<DBaaS> dBaaSList = new ArrayList<DBaaS>();
		try {
			connection = ConnectionPool.getInstance().getConnection();
			preparedStatement = connection.prepareStatement("select * from dbaas order by `id`;");
						
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				DBaaS dBaaS = getEntity(resultSet);
				dBaaSList.add(dBaaS);
			}
		}
		catch(SQLException se) {se.printStackTrace();}
		catch (RuntimeException re) {re.printStackTrace();}
		finally {
            try { resultSet.close(); } catch(Exception e) {}
            try { preparedStatement.close(); } catch(Exception e) {}
            try { connection.close(); } catch(Exception e) {}
        }
		
		return dBaaSList;
	}//all()

	@Override
	public DBaaS find(Integer id) {
		DBaaS dBaaS = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			preparedStatement = connection.prepareStatement("select * from dbaas where `id` = ?;");
			
			preparedStatement.setInt(1, id);
			
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				dBaaS = getEntity(resultSet);	
			}
		}
		catch(SQLException se) {se.printStackTrace();}
		catch (RuntimeException re) {re.printStackTrace();}
		finally {
            try { resultSet.close(); } catch(Exception e) {}
            try { preparedStatement.close(); } catch(Exception e) {}
            try { connection.close(); } catch(Exception e) {}
        }
		return dBaaS;
	} //find()

	@Override
	public void remove(DBaaS resource) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void save(DBaaS resource) {
		try {
			this.connection = ConnectionPool.getInstance().getConnection();
			this.preparedStatement = this.connection.prepareStatement(
					"insert into dbaas " +
					"(`description`, `alias`, `record_date`) " +
					"values (?, ?, now());");
			
			this.preparedStatement.setString(1, resource.getDescription());
			this.preparedStatement.setString(2, resource.getAlias());
			
			this.preparedStatement.executeUpdate();
		}
		catch(SQLException se) {se.printStackTrace();}
		catch (RuntimeException re) {re.printStackTrace();}
		finally {
            try { resultSet.close(); } catch(Exception e) { }
            try { preparedStatement.close(); } catch(Exception e) { }
            try { connection.close(); } catch(Exception e) { }
        }
	}//save()

	@Override
	public void update(DBaaS resource) {
		try {
			this.connection = ConnectionPool.getInstance().getConnection();
			this.preparedStatement = this.connection.prepareStatement(
					"update dbaas " +
					"set `alias` = ?, `description` = ? " +
					"where `id` = ?;");
			
			this.preparedStatement.setString(1, resource.getAlias());
			this.preparedStatement.setString(2, resource.getDescription());
			this.preparedStatement.setInt(3, resource.getId());
			
			this.preparedStatement.executeUpdate();
		}
		catch(SQLException se) {se.printStackTrace();}
		catch (RuntimeException re) {re.printStackTrace();}
		finally {
			try { resultSet.close(); } catch(Exception e) { }
            try { preparedStatement.close(); } catch(Exception e) { }
            try { connection.close(); } catch(Exception e) { }
		}
	}//update()

	@Override
	public DBaaS getEntity(ResultSet resultSet) throws SQLException {
		DBaaS dBaaS = new DBaaS();
		dBaaS.setId(resultSet.getInt("id"));
		dBaaS.setAlias(resultSet.getString("alias"));
		dBaaS.setDescription(resultSet.getString("description"));
		dBaaS.setRecordDate(DataUtil.convertDateToString(resultSet.getTimestamp("record_date")));
		return dBaaS;
	}//getEntity()

}
