public class ZipUtil {

	public static String unzip(String src) {
		
		ByteArrayInputStream bais = null;
		ZipInputStream zis = null;
		try {
			bais = new ByteArrayInputStream(src.getBytes("iso-8859-1"));
			zis = new ZipInputStream(bais);
			zis.getNextEntry(); 
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			final int BUFSIZ = 4096;
			byte inbuf[] = new byte[BUFSIZ];
			int n;
			while ((n = zis.read(inbuf, 0, BUFSIZ)) != -1) {
				baos.write(inbuf, 0, n);
			}
			byte[] data = baos.toByteArray();
			return new String(data);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			try {
				if (zis != null) {
					zis.close();
					zis = null;
				}

				if (bais != null) {
					bais.close();
					bais = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String zip(String src) {

		ByteArrayOutputStream bout = new ByteArrayOutputStream(src.length());
		ZipOutputStream zip = new ZipOutputStream(bout);
		try { 
			ZipEntry entry = new ZipEntry("m"); 
			zip.putNextEntry(entry);			
			byte[] bs = src.getBytes();
			entry.setSize(bs.length);
			zip.write(bs);
			zip.flush();
			zip.closeEntry();
			zip.close();
			bs = bout.toByteArray();
			return new String(bs, "iso-8859-1");
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			try {
				if (zip != null) {
					zip.close();
					zip = null;
				}

				if (bout != null) {
					bout.close();
					bout = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private ZipUtil() {
	}
}
