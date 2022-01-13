package com.mashibing.download;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * description  DownloadWebResourceTest <BR>
 * <p>
 * author: zhao.song
 * date: created in 16:51  2021/11/18
 * company: TRS信息技术有限公司
 * version 1.0
 */
public class DownloadWebResourceTest {

    @Test
    public void testDownload() throws Exception {
        String sPicUrl = "https://img01-static.xinhua-news.com/imageServer/image/bba04f1f3972a8b1/6195e4ecf3e8118bfec5adbe.jpg";
        String sVideoUrl = "https://v.xinhua-news.com/static/view?dest=CtMDEkJnhoczkyISstMTYzNzIxNzE1NjkxOC0vZHNwX0FWL2RzcF92aWRlby8yMDIxLzExLzE4L1h4amZjZUMwMDcwMjJfMjAyMTExMThfQ0JWRk4wQTAwMS5tcDCdQ=";
        URL url = new URL(sVideoUrl);

        URLConnection conn = url.openConnection();
        conn.connect();
        Map<String, List<String>> headerFields = conn.getHeaderFields();
        headerFields.forEach((s,list)->{
            System.out.println(s+"----"+list);
        });

        String sourceFormat = conn.getHeaderField("Content-Type");
        System.out.println(sourceFormat.split("/")[1]);
        /*InputStream input = conn.getInputStream();
        File file = new File("D:\\Users\\liujie\\trs\\trswcmv7\\WCMData\\webpic\\W0202111\\W020211118\\ssfsafas.jpg");
        FileOutputStream fos = new FileOutputStream(file);
        int len=0;
        byte[] cache = new byte[1024];
        while ((len = input.read(cache)) != -1) {
            fos.write(cache, 0, len);
        }
        fos.flush();
        fos.close();
        input.close();*/

    }
}
