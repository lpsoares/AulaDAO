import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DAO {

	private Connection connection = null;

	public DAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/meus_dados", "root", "123456");
		} catch (SQLException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void adiciona(Pessoas pessoa) {
		try {
			String sql = "INSERT INTO Pessoas" +
					"(nome,nascimento,altura) values(?,?,?)";
			PreparedStatement stmt;
			stmt = connection.prepareStatement(sql);
			stmt.setString(1,pessoa.getNome());
			stmt.setDate(2, new Date(
					pessoa.getNascimento().getTimeInMillis()));
			stmt.setDouble(3,pessoa.getAltura());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Pessoas> getLista() {

		List<Pessoas> pessoas = new ArrayList<Pessoas>();

		try {	
			PreparedStatement stmt = this.connection.
					prepareStatement("SELECT * FROM Pessoas");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Pessoas pessoa = new Pessoas();
				pessoa.setId(rs.getInt("id"));
				pessoa.setNome(rs.getString("nome"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("nascimento"));
				pessoa.setNascimento(data);
				pessoa.setAltura(rs.getDouble("altura"));
				pessoas.add(pessoa);
			}
			rs.close();
			stmt.close();

		} catch(SQLException e) {
			System.out.println(e);
		}
		return pessoas;
	}
	
	public void altera(Pessoas pessoa) {
		
	    try {
			String sql = "UPDATE Pessoas SET nome=?, nascimento=?, altura=? " +
		            "WHERE id=?";
		    
	        PreparedStatement stmt = connection.prepareStatement(sql);
	        stmt.setString(1, pessoa.getNome());
	        stmt.setDate(2, new Date(pessoa.getNascimento()
	                .getTimeInMillis()));
	        stmt.setDouble(3, pessoa.getAltura());
	        stmt.setInt(4, pessoa.getId());
	        stmt.execute();
	        stmt.close();
		} catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	public void remove(Integer id) {
	    try {

	        PreparedStatement stmt = connection
	                .prepareStatement("DELETE FROM Pessoas WHERE id=?");
	        stmt.setLong(1, id);
	        stmt.execute();
	        stmt.close();
	    } catch(SQLException e) {
			System.out.println(e);
		} 
	}

	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

}
