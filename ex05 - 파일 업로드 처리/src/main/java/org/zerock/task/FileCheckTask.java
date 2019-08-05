package org.zerock.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.zerock.domain.BoardAttachVO;
import org.zerock.mapper.BoardAttachMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Component
public class FileCheckTask {
	
	@Setter(onMethod_= {@Autowired})
	private BoardAttachMapper attachMapper;
	
	private String getFolderYesterDay() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String str=sdf.format(cal.getTime());
		return str.replace("-", File.separator);
	}
	
	//데이터베이스 파일 리스트와 폴더상 파일 리스트 동기화
	@Scheduled(cron="0 0 2 * * *")
	public void checkFiles() throws Exception{
		log.warn("File Check Task run.............");
		log.warn(new Date());
		
		List<BoardAttachVO> fileList=attachMapper.getOldFiles();
		
		//파일 리스트
		List<Path> fileListPaths=fileList.stream()
								.map(vo->Paths.get("C:\\Users\\Main\\Desktop\\git\\SpringStudy\\ex05\\upload",vo.getUploadPath(),vo.getUuid()+"_"+vo.getFileName()))
								.collect(Collectors.toList());
		//이미지파일은 썸네일파일까지
		fileList.stream().filter(vo->vo.isFileType()==true)
								.map(vo->Paths.get("C:\\Users\\Main\\Desktop\\git\\SpringStudy\\ex05\\upload",vo.getUploadPath(),"s_"+vo.getUuid()+"_"+vo.getFileName()))
								.forEach(p->fileListPaths.add(p));
		
		//삭제파일 목록
		fileListPaths.forEach(p->log.warn(p));
		
		File targetDir=Paths.get("C:\\Users\\Main\\Desktop\\git\\SpringStudy\\ex05\\upload",getFolderYesterDay()).toFile();
		
		//데이터베이스에 없는 파일 리스트(삭제예정)
		File[] removeFiles=targetDir.listFiles(file->fileListPaths.contains(file.toPath())==false);
		
		//삭제
		for(File file:removeFiles) {
			log.warn(file.getAbsoluteFile());
			file.delete();
		}
	}
}
