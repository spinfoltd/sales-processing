package com.mprocessor.sales;

import com.mprocessor.sales.cmds.SCommandTypeEnum;
import com.mprocessor.sales.evnts.ExecServiceUtil;
import com.mprocessor.sales.messgs.RandomMessageGenerator;
import com.mprocessor.sales.model.MBaseMessageType;
import com.mprocessor.sales.model.MMsgType;
import com.mprocessor.sales.model.MProducts;
import com.mprocessor.sales.thrds.MessageQueueProvider;
import com.mprocessor.sales.thrds.MessageSender;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class RandomMessageGeneratorTest {
    MProducts products;
    @Before
    public void init(){
        products = new MProducts();
    }

    @Test
    public void testRandomBetween(){
        {
            IntStream.range(1,5).forEach(
                    (i) -> System.out.println((new Random()).nextInt(1 -1+1))
            );

        }
        {
            IntStream.range(1,5).forEach(
                    (i) -> {
                        int retInt = RandomMessageGenerator.getRandomIntBetwn(1, 1);
                        Assert.assertTrue(retInt == 1);
                    }
            );
        }
        {
            IntStream.range(1,5).forEach(
                    (i) -> {
                        int retInt = RandomMessageGenerator.getRandomIntBetwn(1, 2);
                        Assert.assertTrue(1 <=retInt && retInt <=2 );
                    }
            );
        }
        {
            IntStream.range(1,10).forEach(
                    (i) -> {
                        int retInt = RandomMessageGenerator.getRandomIntBetwn(1, 3);
                        Assert.assertTrue(1 <=retInt && retInt <=3 );
                    }
            );
        }
    }

    @Test
    public void randomOccurenceTest(){
        {
            IntStream.range(1,10).forEach(
                    (i) -> {
                        int retInt = RandomMessageGenerator.getRandomOccurrence();
                        Assert.assertTrue(2 <=retInt && retInt <=3 );
                    }
            );
        }
    }
    @Test
    public void randomOperationTest(){
        {
            IntStream.range(1,10).forEach(
                    (i) -> {
                        String opr = RandomMessageGenerator.getRandomOperation();
                        Assert.assertTrue(Arrays.stream(SCommandTypeEnum.values()).
                                filter( c -> c.getCommandType().equals(opr))
                                .findAny()
                                .isPresent()
                        );
                    }
            );

        }
    }
    @Test
    public void randomMessageTest(){
        {
            IntStream.range(1,10).forEach(
                    (i) -> {
                        MBaseMessageType msg = (new RandomMessageGenerator()).generateMessage();
                        System.out.println(msg.getProductType() +":"+msg.getMsgType()+":" + msg.getValue());
                        System.out.println(msg.getJSon(1));
                        Assert.assertTrue(Arrays.stream(MMsgType.values()).
                                filter( c -> c.equals(msg.getMsgType()))
                                .findAny()
                                .isPresent()
                        );
                    }
            );
        }
    }

    @Test
    public void testExec(){
       Runnable runnableTask = () ->        {
        try {
            System.out.println("XXXX");
            IntStream.range(1,10).forEach(
                    (i) -> {

                        MBaseMessageType msg = (new RandomMessageGenerator()).generateMessage();
                        System.out.println(msg.getJSon(1));
                    }
            );
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        };
        ExecutorService execService = Executors.newFixedThreadPool(1);
        execService.execute(runnableTask);
        execService.shutdown();
        ExecServiceUtil.waitUntilTastFinished(execService);
    }
    
    

    @Test
    public void testExec2(){
        Runnable runnableTask = () -> {
            try {
                System.out.println("XXXX");
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        ExecutorService execService = Executors.newFixedThreadPool(1);
        execService.execute(runnableTask);
        execService.shutdown();
        ExecServiceUtil.waitUntilTastFinished(execService);
        
    }

    @Test
    public void messageSenderTest(){
        MessageQueueProvider que = new MessageQueueProvider();
        MessageSender sender = new MessageSender(que, 20);
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(sender);
        service.shutdown();
    }
}
