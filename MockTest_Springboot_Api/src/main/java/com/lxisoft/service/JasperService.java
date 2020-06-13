package com.lxisoft.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lxisoft.model.AttendedExamBean;

/**
* Service class for jasper report generation
*/
@Service
@Transactional

public class JasperService {

    private final Logger log = LoggerFactory.getLogger(JasperService.class);

    @Autowired
	DataSource dataSource;
    

    /**
     * Gets userexamReport : using javabean.
     * @param list-AttentendedExamBean
     *
     * @return the byte[].
     * @throws JRException  
     */

	public byte[] getReportAsPdfUsingJavaBeans(List<AttendedExamBean> list)throws JRException
	{
//		JasperReport jr=JasperCompileManager.compileReport("src/main/resources/ExamReport.jrxml");
		JasperReport jr=JasperCompileManager.compileReport("src/main/resources/sample.jrxml");
		JRBeanCollectionDataSource collectionDataSource=new JRBeanCollectionDataSource(list);
		
		Map < String , Object > parameters = new HashMap < String ,	Object >();
		
		JasperPrint jp=JasperFillManager.fillReport(jr,parameters,collectionDataSource);
		return JasperExportManager.exportReportToPdf(jp);
		
		
		
	}
	
	 /**
     * Gets certificate of exam : using database
     * @param long id of attendedExam
     *
     * @return the byte[].
     * @throws JRException  
     */

	 public byte[] getReportAsPdfUsingDatabase(long id)throws JRException
		{
			JasperReport jr=JasperCompileManager.compileReport("src/main/resources/Certificate.jrxml");
			
			//preparing parameteres
			Map parameters=new HashMap();
		//	parameters.put("head","Certificate");
			parameters.put("id",id);
			Connection con=null;
			try {
				con=dataSource.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			JasperPrint jp=JasperFillManager.fillReport(jr,parameters,con);
			return JasperExportManager.exportReportToPdf(jp);
			
		}

}
