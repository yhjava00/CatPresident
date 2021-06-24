package member;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import vo.MemberVO;

public class MemberService {
private static final MemberService memberService = new MemberService();
	
	private static MemberDAO memberDAO;
	
	private MemberService() {
		memberDAO = MemberDAO.getMemberDAO();
	}
	
	public static MemberService getMemberService() {
		return memberService;
	}
	
	public int checkId(String id) {
		int a =memberDAO.checkId(id);
		return a;
	}
	public int login(String id, String pw) {
		int result=memberDAO.login(id, pw);

		return result;
//		로그인
//		-2:아이디없음
//		-1:서버오류
//		0:비밀번호틀림
//		1:성공
	}
	public void insertMember(MemberVO mVo) {
		memberDAO.insertMember(mVo);
		MemberVO member = memberDAO.getMember(mVo.getId());
		if(member!=null) {
			directoryCreate(mVo.getId());
		}
	}
	
	public MemberVO getMember(String id) {
		return memberDAO.selectMember(id);
	}
	
	public String withdrawal(String id) {
		int state = memberDAO.deleteMember(id);
		
		if(state!=1) 
			return "error";
		
		return "success";
	}
	
	public String changeProfile(String id, String profile) {
		
		Map<String, String> info = new HashMap<String, String>();
		
		info.put("id", id);
		info.put("profile", profile);
		
		int state = memberDAO.updateProfile(info);
		
		if(state!=1) 
			return "error";
		
		return "success";
	}
	
	public void directoryCreate(String id) {
		String path = "C:\\lyh_java\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\CatPresident\\resources\\profile\\"; // 폴더 경로
		File Folder = new File(path+id);
		// 해당 디렉토리가 없을경우 디렉토리를 생성.
		if (Folder.exists()) {
			File[] folder_list = Folder.listFiles();
			if(folder_list.length == 0 && Folder.isDirectory()){ 
				Folder.delete(); //대상폴더 삭제
				try {
					Folder.mkdir(); // 폴더 생성.
				} catch (Exception e) {
					e.getStackTrace();
				}
			}
		} else {
			try {
				Folder.mkdir(); // 폴더 생성.
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
		File folder1 = new File(path+"profile");//복사할 폴더
		File folder2 = new File(path+id);//복사될 폴더
		directoryCopy(folder1, folder2);
	}
	public void directoryCopy(File sourceF, File targetF) {
		File[] target_file = sourceF.listFiles();
		for (File file : target_file) {
			File temp = new File(targetF.getAbsolutePath() + File.separator + file.getName());
			if (file.isDirectory()) {
				temp.mkdir();
				directoryCopy(file, temp);
			} else {
				FileInputStream fis = null;
				FileOutputStream fos = null;
				try {
					fis = new FileInputStream(file);
					fos = new FileOutputStream(temp);
					byte[] b = new byte[4096];
					int cnt = 0;
					while ((cnt = fis.read(b)) != -1) {
						fos.write(b, 0, cnt);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						fis.close();
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
