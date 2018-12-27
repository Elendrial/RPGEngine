package me.hii488.handlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import me.hii488.interfaces.IInputListener;
import me.hii488.registries.KeyBindRegistry;

public class InputHandler implements MouseListener, KeyListener{
	
	// Possibly replace part of this with a lookup? eg from another class: InputHandler.isKeyDown((KeyBind) k); --- This is in KeyBindRegistry --- (TODO: remove this)
	
	public static InputHandler instance = new InputHandler();
	public static ArrayList<IInputListener> inputUsers = new ArrayList<IInputListener>();
	public static ArrayList<IInputListener> lateInputUsers = new ArrayList<IInputListener>();
	
	public static void addInputListener(IInputListener i){
		inputUsers.add(i);
	}
	
	public static void removeInputListener(IInputListener i) {
		inputUsers.remove(i);
	}
	
	public static void addLateInputListener(IInputListener i){
		lateInputUsers.add(i);
	}
	
	public static void removeLateInputListener(IInputListener i) {
		lateInputUsers.remove(i);
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		for(IInputListener i : inputUsers) i.keyPressed(KeyBindRegistry.getKeyBindedTo(arg0.getKeyCode()));
		for(IInputListener i : lateInputUsers) i.keyPressed(KeyBindRegistry.getKeyBindedTo(arg0.getKeyCode()));
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		for(IInputListener i : inputUsers) i.keyReleased(KeyBindRegistry.getKeyBindedTo(arg0.getKeyCode()));
		for(IInputListener i : lateInputUsers) i.keyReleased(KeyBindRegistry.getKeyBindedTo(arg0.getKeyCode()));
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		for(IInputListener i : inputUsers) i.keyTyped(KeyBindRegistry.getKeyBindedTo(arg0.getKeyCode()));
		for(IInputListener i : lateInputUsers) i.keyTyped(KeyBindRegistry.getKeyBindedTo(arg0.getKeyCode()));
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		boolean blocking = false; // TODO: Maybe make this a string/array? So multiple flags can be passed around 
		for(IInputListener i : inputUsers) blocking = blocking || i.mouseClicked(arg0, blocking);
		for(IInputListener i : lateInputUsers) blocking = blocking || i.mouseClicked(arg0, blocking);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		for(IInputListener i : inputUsers) i.mouseEntered(arg0);
		for(IInputListener i : lateInputUsers) i.mouseEntered(arg0);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		for(IInputListener i : inputUsers) i.mouseExited(arg0);
		for(IInputListener i : lateInputUsers) i.mouseExited(arg0);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		for(IInputListener i : inputUsers) i.mousePressed(arg0);
		for(IInputListener i : lateInputUsers) i.mousePressed(arg0);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		for(IInputListener i : inputUsers) i.mouseReleased(arg0);
		for(IInputListener i : lateInputUsers) i.mouseReleased(arg0);
	}

}
