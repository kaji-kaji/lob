package model;

import java.util.List;

public class PostTimeLogic {
  public void execute(Access access, List<Access> accessList) {
	  
		  accessList.add(0, access); // 先頭に追加 解説①
		  
		  for(int i = 0; i < accessList.size(); i++) {
			  for(int j = i+1; j < accessList.size(); j++ ) {
				  if(accessList.get(i).getUserName().equals(accessList.get(j).getUserName())) {
					  Access ac = new Access(accessList.get(i).getUserName(), accessList.get(j).getInTime(), accessList.get(i).getOutTime());//インスタンスの生成
					  accessList.set(i, ac);
					  accessList.remove(j);
				      break;
				  }
			  }
		  }
  }
}