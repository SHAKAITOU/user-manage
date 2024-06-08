package cn.caam.gs.manage.dbmaintenance.tool;

public class MapperBuildTool {

	private static String ROOTPATH = System.getProperty("user.dir") + System.getProperty("file.separator") + 
											"src" + System.getProperty("file.separator") +
											"main" + System.getProperty("file.separator") +
											"java" + System.getProperty("file.separator");

	private static String PACKAGE_PATH = "sha"+System.getProperty("file.separator")+
											"yasuku"+System.getProperty("file.separator")+
											"domain"+System.getProperty("file.separator")+
											"db"+System.getProperty("file.separator")+
											"custom"+System.getProperty("file.separator")+
											"mapper"+System.getProperty("file.separator");
	
	private static String PACKAGE_NAME = "cn.caam.gs.domain.db.custom.mapper";
	

	public static void main(String[] args) {
		CommonMapperBuild.build(ROOTPATH, PACKAGE_PATH, PACKAGE_NAME);
		SequenceMapperBuild.build(ROOTPATH, PACKAGE_PATH, PACKAGE_NAME);
	}

}
