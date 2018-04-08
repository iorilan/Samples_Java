
public class LoadConfig {
	
	private final static Map<String, String> params = new HashMap<String, String>();
	
	static {
		load();
	}

	private static String rpcIp;
	private static int rpcPort;

	
	private static void load() {
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(LoadConfig.class.getClassLoader()
					.getResourceAsStream("systemConfig.xml"));
			Element root = doc.getRootElement();
			
			rpcIp = root.elementTextTrim("rpcIp");
			rpcPort = Integer.valueOf(root.elementTextTrim("rpcPort"));
			// 读取配置文件的参数配置信息
			Element configs = root.element("configs");
			for (Object obj : configs.elements()) {
				if (obj instanceof Element) {
					Element e = (Element) obj;
					params.put(e.attributeValue("name"), e
							.attributeValue("value"));
				}
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}


	public static String getString(String name) {
		return params.get(name);
	}
	
}