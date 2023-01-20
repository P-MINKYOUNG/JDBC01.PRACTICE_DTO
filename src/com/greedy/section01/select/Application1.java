package com.greedy.section01.select;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.greedy.model.dto.CategoryDTO;

public class Application1 {

	public static void main(String[] args) {
		
		/*TBL_CATEGORY 테이블을 대상으로 한 CRUD 기능 만들기
		 *
		 *1. section01.select.Application1 
		 *	 : 테이블 전체 데이터 조회
		 *	 List<CategoryDTO> 타입으로 처리
		 *
		 *2. section01.select.Application2 
		 *	 : Scanner로 입력 받은 categoryCode에 해당하는 행 데이터 조회
		 *	 CategoryDTO 타입으로 처리
		 *
		 *3. section02.insert.Applcation
		 *4. section03.update.Application
		 *5. section04.delete.Application*/
		
		//xml 생성
		Properties prop = new Properties();
		
		prop.setProperty("keyString", "valueString");
				
		try {
			//new FileOutputStream("파일 경로" , "코멘트")
			prop.storeToXML(new FileOutputStream("mapper/category-query.xml"), "");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		/*1. 테이블 전체 데이터 조회 ----------------------------------------------------*/
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<CategoryDTO> cateList = null;
		CategoryDTO cateDTO = null;
		
		try {
			Properties prop2 = new Properties();
			prop2.load(new FileReader("config/jdbc-config.properties"));
			String driver = prop2.getProperty("driver");
			String url = prop2.getProperty("url");
			
			conn = DriverManager.getConnection(url, prop2);
			
			String query = "SELECT * FROM TBL_CATEGORY";
			
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
						
			cateList = new ArrayList<>();
			
			while(rset.next()) {
				cateDTO = new CategoryDTO();
				
				cateDTO.setCategoryCode(rset.getInt("CATEGORY_CODE"));
				cateDTO.setCategoryName(rset.getString("CATEGORY_NAME"));
				cateDTO.setRefCategoryCode(rset.getInt("REF_CATEGORY_CODE"));				
				
				cateList.add(cateDTO);
			}
			
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
		
		for(CategoryDTO cDTO : cateList) {
			System.out.println(cDTO.toString());
		}

	}

}
