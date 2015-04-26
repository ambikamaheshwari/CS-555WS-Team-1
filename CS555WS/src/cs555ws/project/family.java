package cs555ws.project;

public class family {
	 private String identifier;
	 private String husband;
	 private String wife;
	 private String divorceDate;
	 private String weddingDate;
	 private ArrayList<String> children = new ArrayList<String>();
	 
	
	 
	 public String getIdentifier() {
	  return identifier;
	 }
	 public void setIdentifier(String identifier) {
	  this.identifier = identifier;
	 }
	 public String getHusband() {
	  return husband;
	 }
	 public void setHusband(String husband) {
	  this.husband = husband;
	 }
	 public String getWife() {
	  return wife;
	 }
	 public void setWife(String wife) {
	  this.wife = wife;
	 }
	 public String getDivorceDate() {
		  return divorceDate;
		 }
		 public void setDivorceDate(String divorceDate) {
		  this.divorceDate = divorceDate;
		 }
		 public String getWeddingDate() {
			  return weddingDate;
			 }
			 public void setWeddingDate(String weddingDate) {
			  this.weddingDate = weddingDate;
			 } public void addChildren(String id){
					children.add(id);
			 
	}


			public ArrayList<String> getChildren() {
				return children;
			}


			public void setChildren(ArrayList<String> children) {
				this.children = children;
			}
}
