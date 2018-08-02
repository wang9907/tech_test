package com.summer.tech.javase7;

import java.io.FileWriter;
import java.io.IOException;

import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

public class ScriptEngineDemo {

	public interface Greet {

		String getGreeting(String string);

	}
	public static void main(String[] args) throws Exception {
		ScriptEngineDemo demo = new ScriptEngineDemo();
		 //demo.greet();
		 //demo.useDefaultBinding();
		 //demo.useCustomeBinding();
		 //demo.scriptToFile();
		// demo.scriptContextAttribute();
		//demo.scriptContextBindings();
		 demo.run("print('Hello')");
		//demo.invokeFunction();
		//demo.invokeMethod();
		//demo.useInterface();
	}

	public void greet() throws ScriptException {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");
		if (engine == null) {
			throw new RuntimeException("找不到Javascript语言引擎");
		}
		engine.eval("print('Hello world!');");
	}

	public ScriptEngine getJavaScriptEngine() {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");
		if (engine == null) {
			throw new RuntimeException("找不到Javascript语言引擎");
		}
		return engine;
	}

	public void useDefaultBinding() throws ScriptException {
		ScriptEngine engine = getJavaScriptEngine();
		engine.put("name", "Alex");
		engine.eval("var message='Hello,'+name;");
		engine.eval("print(message)");
		Object obj = engine.get("message");
		System.out.println(obj);
	}

	public void useCustomeBinding() throws ScriptException {
		ScriptEngine engine = getJavaScriptEngine();
		Bindings bindings = new SimpleBindings();
		bindings.put("hobby", "playing games");
		engine.eval("print('I like '+hobby);", bindings);
	}

	// 输出文件
	public void scriptToFile() throws IOException, ScriptException {
		ScriptEngine engine = getJavaScriptEngine();
		ScriptContext context = engine.getContext();
		context.setWriter(new FileWriter("output.txt"));
		engine.eval("print('Hello World');");
	}

	// 自定义属性
	public void scriptContextAttribute() throws ScriptException {
		ScriptEngine engine = getJavaScriptEngine();
		ScriptContext context = engine.getContext();
		context.setAttribute("name", "Alex", ScriptContext.GLOBAL_SCOPE);
		context.setAttribute("name", "Bob", ScriptContext.ENGINE_SCOPE);
		System.out.println(context.getAttribute("name"));
	}

	// 语言绑定对象
	public void scriptContextBindings() throws IOException, ScriptException {
		ScriptEngine engine = getJavaScriptEngine();
		ScriptContext context = engine.getContext();
		Bindings bindings1 = engine.createBindings();
		bindings1.put("name", "Alex");
		context.setBindings(bindings1, ScriptContext.GLOBAL_SCOPE);
		Bindings bindings2 = engine.createBindings();
		bindings2.put("name", "Bod");
		context.setBindings(bindings2, ScriptContext.ENGINE_SCOPE);
		engine.eval("print(name);");
	}

	// 脚本编译例子
	public CompiledScript compile(String scriptText) throws ScriptException {
		ScriptEngine engine = getJavaScriptEngine();
		if (engine instanceof Compilable) {
			CompiledScript script = ((Compilable) engine).compile(scriptText);
			return script;
		}
		return null;
	}

	public void run(String scriptText) throws ScriptException {
		CompiledScript script = compile(scriptText);
		if (script == null) {
			return;
		}
		for (int i = 0; i < 100; i++) {
			script.eval();
		}
	}
	//java调用脚本中顶层方法
	public void invokeFunction() throws ScriptException, NoSuchMethodException{
		ScriptEngine engine = getJavaScriptEngine();
		String scriptText = "function greet(name){print('Hello ,'+name);}";
		engine.eval(scriptText);
		Invocable invocable = (Invocable)engine;
		invocable.invokeFunction("greet", "Alex");
	}
	//java调用脚本中对象成员方法
	public void invokeMethod() throws ScriptException, NoSuchMethodException{
		ScriptEngine engine = getJavaScriptEngine();
		String scriptText = "var obj =  {getGreeting:function(name){return 'Hello '+name}};";
		engine.eval(scriptText);
		Invocable invocable = (Invocable)engine;
		Object scope = engine.get("obj");
		Object result = invocable.invokeMethod(scope, "getGreeting", "Alex1");
		System.out.println(result);
	}
	// 在脚本中实现java接口的示例
	public void useInterface() throws ScriptException{
		ScriptEngine engine = getJavaScriptEngine();
		String scriptText = "function getGreeting(name){return 'Hello '+name};";
		engine.eval(scriptText);
		Invocable invocable=(Invocable)engine;
		Greet greet = invocable.getInterface(Greet.class);
		System.out.println(greet.getGreeting("Alex"));
	}
}
