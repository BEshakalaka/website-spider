import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

/**
 * @Create by codeshuai on : 2020/6/13 11:08
 */
public class Bilibili_Rank_Spider {
    public static void main(String[] args) {
//      设置webdriver路径
        System.setProperty("webdriver.chrome.driver", Bilibili_Rank_Spider.class.getClassLoader().getResource("chromedriver.exe").getPath());
//      创建webdriver
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.bilibili.com/ranking");
//      以上三步后，可以测试是否将浏览器开启
        getDatail(webDriver);
    }
    public static void getDatail(WebDriver webDriver){
        //通过className选择元素：选择类名为rank-item的li标签需要list集合去接
        List<WebElement> rankElements = webDriver.findElements(By.className("rank-item"));
//       for循环遍历rankElements集合
        for (WebElement rankElement : rankElements) {
//          根据class为num的标签获取值作为排名
            String rank = rankElement.findElement(By.className("num")).getText();
//          通过className去选择content下面的class为info的标签
            WebElement info = rankElement.findElement(By.className("content")).findElement(By.className("info"));
//           选择info下标签为a的文本作为视频名
            String view_name = info.findElement(By.xpath("a")).getText();
//          通过getAttribute方法，获取视频的链接
            String src = info.findElement(By.xpath("a")).getAttribute("href");
//          获取播放量和弹幕数的webElemet标签集合
            List<WebElement> plays = info.findElement(By.className("detail")).findElements(By.className("data-box"));
//          播放量
            String play_num = plays.get(0).getText();
//          弹幕量
            String view_num = plays.get(1).getText();
//          获得创作者的名字
            WebElement playElement = info.findElement(By.className("detail")).findElement(By.xpath("a")).findElement(By.className("data-box"));
            String player = playElement.getText();
            String socre = info.findElement(By.className("pts")).findElement(By.xpath("div")).getText();

            System.out.println("排行第"+rank+":  视频名为："+view_name+"" +"  播放链接为："+src+""+
                    "  播放量为："+play_num+"  弹幕数为："+view_num+"" +
                    "  创作者为: "+player+"  得分为："+socre);
        }
    }
}
