package com.greedy.section04.delete;

import static com.greedy.common.JDBCTemplate.close;
import static com.greedy.common.JDBCTemplate.getConnection;

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
		
		Properties prop = new Properties();
		PreparedStatement pstmt = null;
		int result = 0;
		
		/*카테고리 코드를 입력 받아 해당 행을 삭제하는 카테고리*/
		Connection conn = getConnection();
		
		Scanner sc = new Scanner(System.in);
		System.out.print("삭제할 카테고리 코드를 입력하세요 : ");
		int categoryCode = sc.nextInt();
		
		CategoryDTO cateDTO = new CategoryDTO();
		cateDTO.setCategoryCode(categoryCode);
		
		try {
			prop.loadFromXML(new FileInputStream("mapper/category-query.xml"));
			String query = prop.getProperty("deleteCategory");
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cateDTO.getCategoryCode());
			
			result = pstmt.executeUpdate();
			
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn);
			close(pstmt);
		}
		
		if(result > 0) System.out.println("삭제가 완료되었습니다.");
		else System.out.println("삭제에 실패했습니다.");
			
	}

}
