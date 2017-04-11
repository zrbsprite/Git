0.Netty Server
package com.tjbsl.netty.demo0.server;
import com.tjbsl.netty.demo3.time.TimeServerHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 处理数据
 */
public class NettyServer {
    private int port;
    public NettyServer(int port) {
        this.port = port;
    }
    public void run() throws Exception {
        /***
         * NioEventLoopGroup 是用来处理I/O操作的多线程事件循环器，
         * Netty提供了许多不同的EventLoopGroup的实现用来处理不同传输协议。
         * 在这个例子中我们实现了一个服务端的应用，
         * 因此会有2个NioEventLoopGroup会被使用。
         * 第一个经常被叫做‘boss’，用来接收进来的连接。
         * 第二个经常被叫做‘worker’，用来处理已经被接收的连接，
         * 一旦‘boss’接收到连接，就会把连接信息注册到‘worker’上。
         * 如何知道多少个线程已经被使用，如何映射到已经创建的Channels上都需要依赖于EventLoopGroup的实现，
         * 并且可以通过构造函数来配置他们的关系。
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        System.out.println("准备运行端口：" + port);
        try {
            /**
             * ServerBootstrap 是一个启动NIO服务的辅助启动类
             * 你可以在这个服务中直接使用Channel
             */
            ServerBootstrap b = new ServerBootstrap();
            /**
             * 这一步是必须的，如果没有设置group将会报java.lang.IllegalStateException: group not set异常
             */
            b = b.group(bossGroup, workerGroup);
            /***
             * ServerSocketChannel以NIO的selector为基础进行实现的，用来接收新的连接
             * 这里告诉Channel如何获取新的连接.
             */
            b = b.channel(NioServerSocketChannel.class);
            /***
             * 这里的事件处理类经常会被用来处理一个最近的已经接收的Channel。
             * ChannelInitializer是一个特殊的处理类，
             * 他的目的是帮助使用者配置一个新的Channel。
             * 也许你想通过增加一些处理类比如NettyServerHandler来配置一个新的Channel
             * 或者其对应的ChannelPipeline来实现你的网络程序。
             * 当你的程序变的复杂时，可能你会增加更多的处理类到pipline上，
             * 然后提取这些匿名类到最顶层的类上。
             */
            b = b.childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                   //ch.pipeline().addLast(new DiscardServerHandler());//demo1.discard
                   //ch.pipeline().addLast(new ResponseServerHandler());//demo2.echo
                   ch.pipeline().addLast(new TimeServerHandler());//demo3.time
                }
            });
            /***
             * 你可以设置这里指定的通道实现的配置参数。
             * 我们正在写一个TCP/IP的服务端，
             * 因此我们被允许设置socket的参数选项比如tcpNoDelay和keepAlive。
             * 请参考ChannelOption和详细的ChannelConfig实现的接口文档以此可以对ChannelOptions的有一个大概的认识。
             */
            b = b.option(ChannelOption.SO_BACKLOG, 128);
            /***
             * option()是提供给NioServerSocketChannel用来接收进来的连接。
             * childOption()是提供给由父管道ServerChannel接收到的连接，
             * 在这个例子中也是NioServerSocketChannel。
             */
            b = b.childOption(ChannelOption.SO_KEEPALIVE, true);
            /***
             * 绑定端口并启动去接收进来的连接
             */
            ChannelFuture f = b.bind(port).sync();
            /**
             * 这里会一直等待，直到socket被关闭
             */
            f.channel().closeFuture().sync();
        } finally {
            /***
             * 优雅关闭
             */
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
    public static void main(String[] args) throws Exception {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8000;
        }
        new NettyServer(port).run();
        //通过cmd窗口的telnet 127.0.0.1 8000运行
    }
}


1.DISCARD服务(丢弃服务，指的是会忽略所有接收的数据的一种协议)
package com.tjbsl.netty.demo1.discard;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
/**
 * 服务端处理通道.这里只是打印一下请求的内容，并不对请求进行任何的响应
 * DiscardServerHandler 继承自 ChannelHandlerAdapter，
 * 这个类实现了ChannelHandler接口，
 * ChannelHandler提供了许多事件处理的接口方法，
 * 然后你可以覆盖这些方法。
 * 现在仅仅只需要继承ChannelHandlerAdapter类而不是你自己去实现接口方法。
 *
 */
public class DiscardServerHandler extends ChannelHandlerAdapter {
    /***
     * 这里我们覆盖了chanelRead()事件处理方法。
     * 每当从客户端收到新的数据时，
     * 这个方法会在收到消息时被调用，
     * 这个例子中，收到的消息的类型是ByteBuf
     * @param ctx 通道处理的上下文信息
     * @param msg 接收的消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            ByteBuf in = (ByteBuf) msg;
          /*  while (in.isReadable()) {
                System.out.print((char) in.readByte());
                System.out.flush();
            }*/
            //这一句和上面注释的的效果都是打印输入的字符
            System.out.println(in.toString(CharsetUtil.US_ASCII));
        }finally {
            /**
             * ByteBuf是一个引用计数对象，这个对象必须显示地调用release()方法来释放。
             * 请记住处理器的职责是释放所有传递到处理器的引用计数对象。
             */
            ReferenceCountUtil.release(msg);
        }
    }
    /***
     * 这个方法会在发生异常时触发
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        /***
         * 发生异常后，关闭连接
         */
        cause.printStackTrace();
        ctx.close();
    }
}

以上是一个丢弃服务的处理方式，你可以运行后通过telnet来发送消息，来查看是否正常运行，注意console里会打印你的输入内容。

2.ECHO服务（响应式协议）
    到目前为止，我们虽然接收到了数据，但没有做任何的响应。然而一个服务端通常会对一个请求作出响应。让我们学习怎样在ECHO协议的实现下编写一个响应消息给客户端，这个协议针对任何接收的数据都会返回一个响应。

    和discard server唯一不同的是把在此之前我们实现的channelRead()方法，返回所有的数据替代打印接收数据到控制台上的逻辑。
说明NettyServer 还是用上面已经提供的类，只是把这段里的注销部分修改成如下。

package com.tjbsl.netty.demo2.echo;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
/**
 * 服务端处理通道.
 * ResponseServerHandler 继承自 ChannelHandlerAdapter，
 * 这个类实现了ChannelHandler接口，
 * ChannelHandler提供了许多事件处理的接口方法，
 * 然后你可以覆盖这些方法。
 * 现在仅仅只需要继承ChannelHandlerAdapter类而不是你自己去实现接口方法。
 * 用来对请求响应
 */
public class ResponseServerHandler extends ChannelHandlerAdapter {
    /**
     * 这里我们覆盖了chanelRead()事件处理方法。
     * 每当从客户端收到新的数据时，
     * 这个方法会在收到消息时被调用，
     *ChannelHandlerContext对象提供了许多操作，
     * 使你能够触发各种各样的I/O事件和操作。
     * 这里我们调用了write(Object)方法来逐字地把接受到的消息写入
     * @param ctx 通道处理的上下文信息
     * @param msg 接收的消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
         ByteBuf in = (ByteBuf) msg;
         System.out.println(in.toString(CharsetUtil.UTF_8));
        ctx.write(msg);
        //cxt.writeAndFlush(msg)
        //请注意，这里我并不需要显式的释放，因为在进入的时候netty已经自动释放
        // ReferenceCountUtil.release(msg);
    }
    /**
     * ctx.write(Object)方法不会使消息写入到通道上，
     * 他被缓冲在了内部，你需要调用ctx.flush()方法来把缓冲区中数据强行输出。
     * 或者你可以在channelRead方法中用更简洁的cxt.writeAndFlush(msg)以达到同样的目的
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
    /**
     * 这个方法会在发生异常时触发
     *
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        /***
         * 发生异常后，关闭连接
         */
        cause.printStackTrace();
        ctx.close();
    }
}

同样以上运行后，可以通过telnet发送数据，console里会打印出你发送的数据，同时你的命令行界面里应该也会接收到相同的数据。

3.TIME服务(时间协议的服务)
    在这个部分被实现的协议是TIME协议。和之前的例子不同的是在不接受任何请求时他会发送一个含32位的整数的消息，并且一旦消息发送就会立即关闭连接。在这个例子中，你会学习到如何构建和发送一个消息，然后在完成时主动关闭连接。


    因为我们将会忽略任何接收到的数据，而只是在连接被创建发送一个消息，所以这次我们不能使用channelRead()方法了，代替他的是，我们需要覆盖channelActive()方法，下面的就是实现的内容：


说明NettyServer 还是用上面已经提供的类，只是把这段里的注销部分修改成如下。


1	//ch.pipeline().addLast(new DiscardServerHandler());        
2	//ch.pipeline().addLast(new ResponseServerHandler());
3	ch.pipeline().addLast(new TimeServerHandler());
TimeServerHandler类的如下：

package com.tjbsl.netty.demo3.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;

import java.util.Scanner;

public class TimeServerHandler extends ChannelHandlerAdapter {
    /**
     * channelActive()方法将会在连接被建立并且准备进行通信时被调用。
     * 因此让我们在这个方法里完成一个代表当前时间的32位整数消息的构建工作。
     *
     * @param ctx
     */
    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        /*Scanner cin=new Scanner(System.in);
        System.out.println("请输入发送信息：");
        String name=cin.nextLine();*/
        String name="HelloWorld!";
        /**
         * 为了发送一个新的消息，我们需要分配一个包含这个消息的新的缓冲。
         * 因为我们需要写入一个32位的整数，因此我们需要一个至少有4个字节的ByteBuf。
         * 通过ChannelHandlerContext.alloc()得到一个当前的ByteBufAllocator，
         * 然后分配一个新的缓冲。
         */
        final ByteBuf time = ctx.alloc().buffer(4);
        time.writeBytes(name.getBytes());
        /***
         * 和往常一样我们需要编写一个构建好的消息
         * 。但是等一等，flip在哪？难道我们使用NIO发送消息时不是调用java.nio.ByteBuffer.flip()吗？
         * ByteBuf之所以没有这个方法因为有两个指针，
         * 一个对应读操作一个对应写操作。
         * 当你向ByteBuf里写入数据的时候写指针的索引就会增加，
         * 同时读指针的索引没有变化。
         * 读指针索引和写指针索引分别代表了消息的开始和结束。
         * 比较起来，NIO缓冲并没有提供一种简洁的方式来计算出消息内容的开始和结尾，
         * 除非你调用flip方法。
         * 当你忘记调用flip方法而引起没有数据或者错误数据被发送时，
         * 你会陷入困境。这样的一个错误不会发生在Netty上，
         * 因为我们对于不同的操作类型有不同的指针。
         * 你会发现这样的使用方法会让你过程变得更加的容易，
         * 因为你已经习惯一种没有使用flip的方式。
         * 另外一个点需要注意的是ChannelHandlerContext.write()(和writeAndFlush())方法会返回一个ChannelFuture对象，
         * 一个ChannelFuture代表了一个还没有发生的I/O操作。
         * 这意味着任何一个请求操作都不会马上被执行，
         * 因为在Netty里所有的操作都是异步的。
         * 因此你需要在write()方法返回的ChannelFuture完成后调用close()方法，
         * 然后当他的写操作已经完成他会通知他的监听者。
         */
        final ChannelFuture f = ctx.writeAndFlush(time); // (3)
        /**
         * 当一个写请求已经完成是如何通知到我们？
        * 这个只需要简单地在返回的ChannelFuture上增加一个ChannelFutureListener。
         * 这里我们构建了一个匿名的ChannelFutureListener类用来在操作完成时关闭Channel。
         */
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                assert f == future;
                /***
                 * 请注意,close()方法也可能不会立马关闭，他也会返回一个ChannelFuture。
                 */
                ctx.close();
            }
        });
    }
    //接收结果
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("client:"+buf.toString(CharsetUtil.UTF_8));
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}

4.Time客户端
    不像DISCARD和ECHO的服务端，对于TIME协议我们需要一个客户端因为人们不能把一个32位的二进制数据翻译成一个日期或者日历。在这一部分，我们将会讨论如何确保服务端是正常工作的，并且学习怎样用Netty编写一个客户端。

    在Netty中,编写服务端和客户端最大的并且唯一不同的使用了不同的BootStrap和Channel的实现。

package com.tjbsl.netty.demo3.time.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TimeClient {
    public static void main(String[] args) throws Exception {
        String host = "127.0.0.1";
        int port =8000;
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            /**
             * 如果你只指定了一个EventLoopGroup，
             * 那他就会即作为一个‘boss’线程，
             * 也会作为一个‘workder’线程，
             * 尽管客户端不需要使用到‘boss’线程。
             */
            Bootstrap b = new Bootstrap(); // (1)
            b.group(workerGroup); // (2)
            /**
             * 代替NioServerSocketChannel的是NioSocketChannel,这个类在客户端channel被创建时使用
             */
            b.channel(NioSocketChannel.class); // (3)
            /**
             * 不像在使用ServerBootstrap时需要用childOption()方法，
             * 因为客户端的SocketChannel没有父channel的概念。
             */
            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new TimeClientHandler());
                }
            });
            //用connect()方法代替了bind()方法
            ChannelFuture f = b.connect(host, port).sync();
            //等到运行结束，关闭
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}