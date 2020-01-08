// package com.summer.tech.websocket;
//
// import java.io.IOException;
// import java.nio.ByteBuffer;
// import java.nio.CharBuffer;
// import java.nio.charset.Charset;
//
// import javax.servlet.http.HttpServletRequest;
//
// import org.apache.catalina.websocket.MessageInbound;
// import org.apache.catalina.websocket.StreamInbound;
// import org.apache.catalina.websocket.WebSocketServlet;
// import org.apache.catalina.websocket.WsOutbound;
//
// public class Tomcat7Test extends WebSocketServlet {
// @Override
// protected StreamInbound createWebSocketInbound(String arg0,
// HttpServletRequest arg1) {
// return new MsgInbound();
// }
//
// private class MsgInbound extends MessageInbound {
//
// protected void onClose(int status) {
// System.out.println("关闭");
// super.onClose(status);
// }
//
// protected void onOpen(WsOutbound outbound) {
// System.out.println("连接开始");
// super.onOpen(outbound);
// }
//
// @Override
// protected void onBinaryMessage(ByteBuffer buffer) throws IOException {
// Charset charset = Charset.forName("UTF-8");
// System.out.println("收到消息：" + charset.decode(buffer));
//
// }
//
// @Override
// protected void onTextMessage(CharBuffer message) throws IOException {
// System.out.println(message.toString());
// // 将websocket传过来的值返回回去
// WsOutbound outbound = this.getWsOutbound();
// outbound.writeTextMessage(message);
// outbound.flush();
// }
// }
// }