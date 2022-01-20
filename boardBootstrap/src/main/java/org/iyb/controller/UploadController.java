package org.iyb.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.iyb.domain.AttachFileDTO;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnailator;

@Controller
public class UploadController {
	@GetMapping("upload")//form태그
	public void uploadForm() {
		System.out.println("파일 업로드 화면");
	}
	@GetMapping("uploadAjax")//div태그
	public void uploadAjaxForm() {
		System.out.println("파일 업로드 화면");
	}
	//upload.jsp에서 form태그를 이용해서 파일 업로드
	@PostMapping("uploadAction")
	public void uploadAction(MultipartFile[] uploadFile) {
		
		//파일 업로드할 경로 지정
		String uploadFolder="C:\\Users\\GreenArt\\upload";
		
		for(MultipartFile multipartFile : uploadFile) {//uploadFile배열에 변수를 하나 만들어 값을  multipartFile에 저장하여 출력
			System.out.println("업로드 파일 이름 = " + multipartFile.getOriginalFilename());//사용자가 업로드 한 실제 파일 이름
			System.out.println("업로드 파일 크기 = " + multipartFile.getSize());//사용자가 업로드 한 실제 파일 크기
			System.out.println("업로드 파일 형식 = " + multipartFile.getContentType());//사용자가 업로드 한 실제 파일 형식
			
			//File saveFile=new File();//File은 기본생성자가 없기 때문에 꼭 매개변수를 작성해야 한다.
			//uploadFolder에 저장되어 있는 경로로 실제 파일명으로 저장.
			//multipartFile.getOriginalFilename()을 uploadFolder경로에 저장
			File saveFile=new File(uploadFolder,multipartFile.getOriginalFilename());//이름을 저장해야 하기 때문에
			
			try {
				multipartFile.transferTo(saveFile);//transferTo : 실제로 지정한 폴더에 업로드를 시켜주는 역활, 예외처리 필수
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//end try
		}//for문 end
	}
	
	//년/월/일 단위의 폴더를 생성하기 위한 작업. 폴더 이름 추출하여 리턴
	private String getFolder() {
		//현재날짜를 추출(요일 월 일 시:분:초 KST 년도)
		Date date = new Date();//util//기본생성자 호출
		
		//->2020-01-18로 출력하기(yyyy-mm-dd 형식으로 변경(시간 빼기))
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//MM은 대문자 필수 미닛하고 구별하기 위해
		
		//2020-01-18
		String str = sdf.format(date);
		System.out.println("date="+date);
		System.out.println(str.replace("-", File.separator));
		//str.replace("-", File.separator);
		
		//2020-01-18 -> 2022\01\18로 변경
		return str.replace("-", File.separator);
	}
	
	//썸네일 이미지 생성을 할 것인지 안 할 것인지에 대한 판단하는 메소드(사용자가 업로드 한 파일이 이미지이면 생성, 그렇지 않으면 생성 안함)
	private boolean checkImage(File file) {//이미지인지 아닌지 확인하기 위해 매개변수로 file을 받아야 함  //예외처리
		try {
			String contentType=Files.probeContentType(file.toPath());//파일의 타입을 알아내는 probeContentType메소드 호출하여 사용
			return contentType.startsWith("image");//그 파일의 타입이 image이면 true, 그렇지 않으면 false
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	//uploadAjax.jsp에서 ajax를 이용해서 파일 업로드 후 controller연결
	@PostMapping(value="uploadAjaxAction",produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})
	//ajax를 통해서 호출하면 model을 사용하지 않고 ResponseEntity를 사용하여 웹페이지로 return 시킨다.
	public ResponseEntity <ArrayList<AttachFileDTO>> uploadAjaxAction(MultipartFile[] uploadFile) {
		//AttachFileDTO에 저장되는 값이 여러파일에 대한 값이면 배열로 처리가 되어야 하므로 ArrayList타입이 되어야 함. 배열에 저장하여 여러개가 나올 수 있게 함.
		ArrayList<AttachFileDTO> list = new ArrayList<>();
		
		//파일 업로드할 경로 지정
		String uploadFolder="C:\\Users\\GreenArt\\upload";//날짜가 없는 것
		
		//폴더 생성                                     		(기존 폴더, 현재 폴더)를 결합
		File uploadPath = new File(uploadFolder, getFolder());//날짜가 있는 것 //실제 업로드 경로
		System.out.println("uploadPath="+uploadPath);
		
		//날짜는 계속 바뀌기 때문에 날짜 부분만 저장
		String uploadFolderPath=getFolder();
		
		//현재 만들려고 하는 폴더가 없으면
		if(uploadPath.exists()==false) {//exists(), mkdirs() 인스턴스 매서드
			//폴더 생성
			uploadPath.mkdirs();//mkdirs:Make directory=>폴더를 만든다
		}
		
		for(MultipartFile multipartFile : uploadFile) {//uploadFile배열에 변수를 하나 만들어 값을  multipartFile에 저장하여 출력
			System.out.println("업로드 파일 이름 = " + multipartFile.getOriginalFilename());//사용자가 업로드 한 실제 파일 이름
			System.out.println("업로드 파일 크기 = " + multipartFile.getSize());//사용자가 업로드 한 실제 파일 크기
			System.out.println("업로드 파일 형식 = " + multipartFile.getContentType());//사용자가 업로드 한 실제 파일 형식
			
			//UploadController에 있는 uploadAjaxAction메소드에서 AttachFileDTO를 사용해서 값을 저장해야 된는데
			//이럴 경우 UploadController에 AttachFileDTO가 없으면 사용을 할 수 없다.
			//그래서 UploadController에 AttachFileDTO 포함 시켜서 사용하여 값을 저장함.
			AttachFileDTO attachdto = new AttachFileDTO();//포함관계 사용법
			
			//실제 파일명 저장
			String uploadFileName= multipartFile.getOriginalFilename();//가공해야 함//uuid가 빠진 업로드된 파일 이름
			
			//실제 파일명(uploadFileName)을 AttachFileDTO클래스(attachdto)에 fileName에 저장(setFileName)
			attachdto.setFileName(uploadFileName);
			
			//중복이 되지 않는 임의의 문자열을 생성
			UUID uuid = UUID.randomUUID();//.randomUUID(); : 클래스 메서드
			
			//UUID+"_"+getOriginalFilename()의 조합으로 파일명을 uploadFileName에 저장
			uploadFileName=uuid.toString()+"_"+uploadFileName;
			
			//File saveFile=new File();//File은 기본생성자가 없기 때문에 꼭 매개변수를 작성해야 한다.
			//uploadFolder에 저장되어 있는 경로로 실제 파일명으로 저장.
			File saveFile=new File(uploadPath,uploadFileName);//이름을 저장해야 하기 때문에
			
			try {
				//saveFile변수에 저장되어 있는 폴더명으로 파일을 보내라.
				multipartFile.transferTo(saveFile);//transferTo : 실제로 지정한 폴더에 업로드를 시켜주는 역활, 예외처리 필수
				
				//실제 업로드 경로(uploadFolderPath)을 AttachFileDTO클래스(attachdto)에 uploadPath에 저장(setUploadPath)
				attachdto.setUploadPath(uploadFolderPath); //try문 안에 없어도 된다. for문 안에만 있으면 된다.
				
				//uuid값(UUID)을 AttachFileDTO클래스(attachdto)에 uploadPath에 저장(setUuid)
				attachdto.setUuid(uuid.toString()); //try문 안에 없어도 된다. for문 안에만 있으면 된다. //uuid는 클래스타입이므로 문자로 바꿔줘야 한다.
				
				if(checkImage(saveFile)) {//이미지 파일이면
					//FileType값(image)을 AttachFileDTO클래스(attachdto)에 uploadPath에 저장(setImage)
					attachdto.setImage(true); //try문 안에 없어도 된다. for문 안에만 있으면 된다.
					
					//썸네일을 파일을 생성하기 전에 썸네일 파일 이름을 추출
					FileOutputStream thumnail = new FileOutputStream(new File(uploadPath,"s_"+uploadFileName));//FileOutput 파일을 밖으로 뺸다
					
					//썸네일을 파일을 생성함
					Thumbnailator.createThumbnail(multipartFile.getInputStream(),thumnail, 100, 100);
					thumnail.close();//썸네일 종료(메모리 공간 함수)
				}
				
				list.add(attachdto);//add : 값을 넣을 때 사용하는 메서드 
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//end try
		}//for문 end
		//통신상태가 정상적(HttpStatus.OK)이면 ArrayList(list)에 저장되어 있는 값을 웹브라우저(uploadAjax.js에 있는 ajax에 success)에 보내라
		return new ResponseEntity<>(list,HttpStatus.OK);//list는 uploadAjax.js의 ajax에 result로 넘어간다.
	}
	//파일 업로드한 파일타입이 이미지일 때 웹브라우저에 이미지를 띄우기 위해
	@GetMapping("display")
	public ResponseEntity<byte[]> getFile(String fileName) {//getfile()은 문자열로 파일의 경로가 포함된 fileName을 매개변수 받고 byte[](이진수)를 전송
		System.out.println("url주소를 통한 fileName="+fileName);
		
		File file = new File("C:\\Users\\GreenArt\\upload\\"+fileName);
		System.out.println("file="+file);
		ResponseEntity<byte[]> result = null;
		//byte[]로 이미지 파일의 데이터를 전송할 때 브라우저에 보내는 MIME타입이 파일의 종류(jpg, png, xls, ppt...)에 따라서 달라진다.
		//이 부분을 해결하기 위해서 probeContentType()을 이용해서 MIME타입 데이터를 Http의 헤더 메세지에 포함할 수 있도록 처리
		try {
			HttpHeaders header = new HttpHeaders();
			result=new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),header,HttpStatus.OK);
		} catch (IOException e) {// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	//파일 업로드한 파일타입이 이미지가 아닐때(.txt, .xls, .ppt) 웹브라우저를 통해서 download할 수 있도록 한다.
	//consumes : 들어오는 데이터 타입 정의(생략가능) //produces : 반환하는 데이터 타입 정의(생략가능)//*생략을 하게 되면, 웹브라우저가 알아서 타입을 판단(내가 원하는 타입이 아닐 수도 있음)*
	//웹브라우저가 '이 파일은 download해야 하는 파일입니다.' 라는 것을 인지할 수 있도록 반환이 되어야 한다. 그러기 위해서는 MediaType.APPLICATION_OCTET_STREAM_VALUE 타입으로 반환데이터 타입을 선언한다.
	@GetMapping(value="download",produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> downloadFile(String fileName){//Resource core로 import
		System.out.println("download fileName="+fileName);
		
		Resource resource = new FileSystemResource("C:\\Users\\GreenArt\\upload\\"+fileName);
		System.out.println("download resource="+resource);
		
		String resourceName = resource.getFilename();
		HttpHeaders header = new HttpHeaders();
		try {
			header.add("Content-Disposition", "attachment; filename="+new String(resourceName.getBytes("UTF-8"),"ISO-8859-1"));
		} catch (IOException e) {// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(resource,header,HttpStatus.OK);
	}
}