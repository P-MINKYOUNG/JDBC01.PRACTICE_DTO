package com.greedy.section03.update;

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
		
		/*카테고리 코드를 입력 받아 이름과 상위 카테고리를 변경하는 기능*/
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("변경할 카테고리 코드를 입력하세요 : ");
		int categoryCode = sc.nextInt();
		sc.nextLine();
		System.out.print("새롭게 변경할 카테고리 이름을 입력하세요 : ");
		String categoryName = sc.nextLine();
		System.out.print("새롭게 변경할 상위 카테고리 코드를 입력하세요 : ");
		int refCategoryCode = sc.nextInt();
		
		int result = 0;
		
		CategoryDTO cateDTO = new CategoryDTO();
		cateDTO.setCategoryCode(categoryCode);
		cateDTO.setCategoryName(categoryName);
		cateDTO.setRefCategoryCode(refCategoryCode);
		
		
		try {
			Properties prop = new Properties();
			prop.loadFromXML(new FileInputStream("mapper/category-query.xml"));
			
			String query = prop.getProperty("updateCategory");
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cateDTO.getCategoryName());
			pstmt.setInt(2, cateDTO.getRefCategoryCode());
			pstmt.setInt(3, cateDTO.getCategoryCode());
			
			result = pstmt.executeUpdate();
			
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn);
			close(pstmt);
		}
		
		if(result > 0) System.out.println("업데이트 성공");
		else System.out.println("업데이트 실패");

	}

}
