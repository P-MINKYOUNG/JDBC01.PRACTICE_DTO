package com.greedy.section01.select;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import com.greedy.model.dto.CategoryDTO;


public class Application2 {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		CategoryDTO cateDTO = null;
		
		
		try {
			Properties prop = new Properties();
			prop.load(new FileReader("config/jdbc-config.properties"));
			
			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			
			conn = DriverManager.getConnection(url, prop);		
			
			/*Scanner로 입력 받은 categoryCode에 해당하는 행 데이터 조회*/
			Scanner sc = new Scanner(System.in);
			System.out.print("조회할 카테고리 번호를 입력하세요 : ");
			int categoryCode = sc.nextInt();
			
			Properties prop2 = new Properties();
			prop2.loadFromXML(new FileInputStream("mapper/category-query.xml"));
			
			String query = prop2.getProperty("selectCategory");
			

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, categoryCode);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
			cateDTO = new CategoryDTO();
			
			cateDTO.setCategoryCode(rset.getInt("CATEGORY_CODE"));
			cateDTO.setCategoryName(rset.getString("CATEGORY_NAME"));
			cateDTO.setRefCategoryCode(rset.getInt("REF_CATEGORY_CODE"));
			cateDTO.setRefCategoryName(rset.getString("T2"));
			} else System.out.println("해당하는 카테고리 번호가 없습니다.");
									
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(cateDTO.toString());
		
	}

}
