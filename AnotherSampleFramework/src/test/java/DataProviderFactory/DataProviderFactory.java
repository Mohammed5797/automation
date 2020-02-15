package DataProviderFactory;

public class DataProviderFactory {
	// get the class configdataprovider and references it back to getconfig
	public static ConfigDataProvider getConfig()
	{
		ConfigDataProvider config = new ConfigDataProvider();
		
		return config;
	}// get my class and reference it back to getexcel
	public static ExcelDataProvider getExcel()
	{
	ExcelDataProvider excel = new ExcelDataProvider();
	
	return excel;
	

}
}