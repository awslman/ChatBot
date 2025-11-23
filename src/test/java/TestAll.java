import Bots.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 聊天机器人项目综合测试类
 * 包含对各种聊天机器人模式、菜单功能以及核心组件的测试用例
 */
public class TestAll {
    /**
     * 捕获标准输出流的字节数组输出流
     */
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    
    /**
     * 捕获标准错误流的字节数组输出流
     */
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    
    /**
     * 保存原始的标准输出流
     */
    private final PrintStream originalOut = System.out;
    
    /**
     * 保存原始的标准错误流
     */
    private final PrintStream originalErr = System.err;
    
    /**
     * 保存原始的标准输入流
     */
    private InputStream originalIn;

    /**
     * 在每个测试方法执行前设置测试环境
     * 重定向标准输出、错误输出和输入流以便捕获和验证
     */
    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        originalIn = System.in;
    }

    /**
     * 在每个测试方法执行后恢复原始的流设置
     * 确保测试之间不会相互影响
     */
    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
        System.setIn(originalIn);
    }

    /**
     * 测试命令模式聊天机器人
     * 验证基本的命令响应功能是否正常工作
     *
     * @throws InterruptedException 当线程被中断时抛出
     */
    @Test
    public void testCommandModeBot() throws InterruptedException {
        String input = "name\nquit\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        SimpleChatBotCommandMode bot = new SimpleChatBotCommandMode();
        
        Thread botThread = new Thread(bot::run);
        botThread.start();
        
        // 等待线程启动并处理输入
        TimeUnit.SECONDS.sleep(1);
        
        botThread.interrupt();
        
        String output = outContent.toString();
        assertTrue(output.contains("我的名字是ChatBot"), "应返回机器人姓名");
    }

    /**
     * 测试OpenNLP模式聊天机器人
     * 验证使用OpenNLP进行自然语言处理的基本功能
     *
     * @throws InterruptedException 当线程被中断时抛出
     */
    @Test
    public void testOpenNLPBot() throws InterruptedException {
        String input = "你好\nquit\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        SimpleChatBotUsedOpenNLP bot = new SimpleChatBotUsedOpenNLP();
        
        Thread botThread = new Thread(bot::run);
        botThread.start();
        
        TimeUnit.SECONDS.sleep(1);
        
        botThread.interrupt();
        
        String output = outContent.toString();
        assertFalse(output.isEmpty(), "应该有输出内容");
    }

    /**
     * 测试Stanford CoreNLP模式聊天机器人
     * 验证使用Stanford CoreNLP进行自然语言处理的基本功能
     *
     * @throws InterruptedException 当线程被中断时抛出
     */
    @Test
    public void testStanfordNLPBot() throws InterruptedException {
        String input = "你好\nquit\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        SimpleChatBotUsedStanfordNLP bot = new SimpleChatBotUsedStanfordNLP();
        
        Thread botThread = new Thread(bot::run);
        botThread.start();
        
        TimeUnit.SECONDS.sleep(1);
        
        botThread.interrupt();
        
        String output = outContent.toString();
        assertFalse(output.isEmpty(), "应该有输出内容");
    }

    /**
     * 测试NLTK模式聊天机器人
     * 验证使用NLTK进行自然语言处理的基本功能
     *
     * @throws InterruptedException 当线程被中断时抛出
     */
    @Test
    public void testNLTKBot() throws InterruptedException {
        String input = "你好\nquit\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        SimpleChatBotUsedNLTK bot = new SimpleChatBotUsedNLTK();
        
        Thread botThread = new Thread(bot::run);
        botThread.start();
        
        TimeUnit.SECONDS.sleep(1);
        
        botThread.interrupt();
        
        String output = outContent.toString();
        assertFalse(output.isEmpty(), "应该有输出内容");
    }

    /**
     * 测试主程序菜单功能
     * 验证主菜单界面是否正确显示
     *
     * @throws InterruptedException 当线程被中断时抛出
     */
    @Test
    public void testMainMenu() throws InterruptedException {
        String input = "1\nquit\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Thread mainThread = new Thread(() -> {
            try {
                Main.main(new String[]{});
            } catch (Exception e) {
                // 忽略中断异常
            }
        });
        mainThread.start();
        
        TimeUnit.SECONDS.sleep(1);
        
        mainThread.interrupt();
        
        String output = outContent.toString();
        assertTrue(output.contains("简易聊天机器人"), "应显示主菜单");
        assertTrue(output.contains("模式1"), "应显示模式1选项");
    }

    /**
     * 测试NLP工具选择菜单
     * 验证NLP工具选择界面是否正确显示
     *
     * @throws InterruptedException 当线程被中断时抛出
     */
    @Test
    public void testNLPMenu() throws InterruptedException {
        String input = "2\n1\nquit\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Thread mainThread = new Thread(() -> {
            try {
                Main.main(new String[]{});
            } catch (Exception e) {
                // 忽略中断异常
            }
        });
        mainThread.start();
        
        TimeUnit.SECONDS.sleep(1);
        
        mainThread.interrupt();
        
        String output = outContent.toString();
        assertTrue(output.contains("Standford CoreNLP"), "应显示Stanford NLP选项");
    }

    /**
     * 测试关键词匹配功能
     * 验证基于关键词的命令识别是否正常工作
     *
     * @throws InterruptedException 当线程被中断时抛出
     */
    @Test
    public void testKeywordMatching() throws InterruptedException {
        String input = "time\nquit\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        SimpleChatBotCommandMode bot = new SimpleChatBotCommandMode();
        
        Thread botThread = new Thread(bot::run);
        botThread.start();
        
        TimeUnit.SECONDS.sleep(1);
        
        botThread.interrupt();
        
        String output = outContent.toString();
        assertTrue(output.contains("现在的时间是"), "应返回当前时间");
    }

    /**
     * 测试历史记录功能
     * 验证对话历史记录的显示功能是否正常
     *
     * @throws InterruptedException 当线程被中断时抛出
     */
    @Test
    public void testHistoryFunction() throws InterruptedException {
        String input = "history\nquit\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        SimpleChatBotCommandMode bot = new SimpleChatBotCommandMode();
        
        Thread botThread = new Thread(bot::run);
        botThread.start();
        
        TimeUnit.SECONDS.sleep(1);
        
        botThread.interrupt();
        
        String output = outContent.toString();
        assertTrue(output.contains("暂无对话记录") || output.contains("对话历史记录"), "应显示历史记录相关信息");
    }

    /**
     * 测试文件存在性
     * 验证项目必需的配置文件和资源文件是否存在
     */
    @Test
    public void testResourceFilesExist() {
        assertTrue(Files.exists(Paths.get("src/main/resources/app_config.json")), "应存在app_config.json配置文件");
        assertTrue(Files.exists(Paths.get("src/main/resources/database.ini")), "应存在database.ini配置文件");
        assertTrue(Files.exists(Paths.get("src/main/resources/intent-training-data.txt")), "应存在intent-training-data.txt训练数据文件");
    }
}
