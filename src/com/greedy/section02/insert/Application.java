package com.greedy.section02.insert;

import static com.greedy.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import com.greedy.model.dto.CategoryDTO;

public class Application {

	public static void main(String[] args) {
		
		Connection conn = getConnection();
		PreparedStatement stmt = null;
		CategoryDTO cateDTO = new CategoryDTO();
		
		Scanner sc = new Scanner(System.in);

		System.out.print("카테고리 이름을 입력하세요 : ");
		String categoryName = sc.nextLine();
		System.out.print("상위 카테고리 번호를 입력하세요 : ");
		int refCategoryCode = sc.nextInt();
		
		int result = 0;
		
		cateDTO.setCategoryName(categoryName);
		cateDTO.setRefCategoryCode(refCategoryCode);
	
		try {	
			Properties prop = new Properties();
			prop.loadFromXML(new FileInputStream("mapper/category-query.xml"));
			
			String query = prop.getProperty("insertCategory");
			
			stmt = conn.prepareStatement(query);
			stmt.setString(1, cateDTO.getCategoryName());
			stmt.setInt(2, cateDTO.getRefCategoryCode());
			
			result = stmt.executeUpdate();
			
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(conn);
		}
		
		if(result > 0) System.out.println("카테고리 삽입 성공!");
		else System.out.println("카테고리 삽입 실패");

	}

}
