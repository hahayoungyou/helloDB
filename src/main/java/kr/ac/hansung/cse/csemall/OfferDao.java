package kr.ac.hansung.cse.csemall;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component("offerDao")
public class OfferDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public int getRowCount() {
		String sqlStatement = "select count(*) from offers";
		return jdbcTemplate.queryForObject(sqlStatement, Integer.class);// record 갯수
	}
	//cRud method
	// query and return a single object
	public Offer getOffer(String name) {

		String sqlStatement = "select * from offers where name=?";
		return jdbcTemplate.queryForObject(sqlStatement, new Object[] {name}, new RowMapper<Offer>() {
			@Override
			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Offer offer = new Offer();
				offer.setId(rs.getInt("id"));
				offer.setName(rs.getString("name"));
				offer.setEmail(rs.getString("email"));
				offer.setText(rs.getString("text"));

				return offer;
			}

		});

	}
	//cRud method
	// query and return a multiple object
	public List<Offer> getOffers() {

		String sqlStatement = "select * from offers ";
		return jdbcTemplate.query(sqlStatement, new RowMapper<Offer>() {

			@Override
			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Offer offer = new Offer();
				offer.setId(rs.getInt("id"));
				offer.setName(rs.getString("name"));
				offer.setEmail(rs.getString("email"));
				offer.setText(rs.getString("text"));

				return offer;
			}

		});

	}
	//Crud method
	public boolean insert(Offer offer) {
		//id는 auto_increment
		String name=offer.getName();
		String email=offer.getEmail();
		String text=offer.getText();
		
		String sqlStatement = "insert into offers(name,email,text) values(?,?,?) ";//placeholder
		
		return (jdbcTemplate.update(sqlStatement, new Object[] {name,email,text})==1);
	}

	//crUd method
	public boolean update(Offer offer) {
		int id=offer.getId(); 
		String name=offer.getName();
		String email=offer.getEmail();
		String text=offer.getText();
		
		String sqlStatement = "update offers set name=?,email=?,text=? where id=?";//placeholder
		
		return (jdbcTemplate.update(sqlStatement, new Object[] {name,email,text,id})==1);
	}
	//cruD method
		public boolean delete(int id) {
		
			String sqlStatement = "delete from offers where id=?";//placeholder
			
			return (jdbcTemplate.update(sqlStatement, new Object[] {id})==1);
		}
}// 빈으로 등록하고 의존성주입을 통해 데이터 소스 주입
