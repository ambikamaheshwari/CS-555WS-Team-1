package cs555ws.project;

public class family {
	 private String identifier;
	 private String husband;
	 private String wife;
	 private String weddingDate;
	
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
	 public void setWeddingDate(String weddingDate)
		{
			this.weddingDate=weddingDate;
		}
		public String getWeddingDate()
		{
			return weddingDate;
		}
		
	}
