package me.hii488.handlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import me.hii488.interfaces.IInputListener;

public class InputHandler implements MouseListener, KeyListener{
	
	// TODO: Possibly replace part of this with a lookup? eg from another class: InputHandler.isKeyDown((KeyBind) k);
	
	public static InputHandler instance = new InputHandler();
	public static ArrayList<IInputListener> inputUsers = new ArrayList<IInputListener>();
	
	public static void addInputUser(IInputListener i){
		inputUsers.add(i);
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		for(IInputListener i : inputUsers) i.keyPressed(arg0);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		for(IInputListener i : inputUsers) i.keyReleased(arg0);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		for(IInputListener i : inputUsers) i.keyTyped(arg0);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		for(IInputListener i : inputUsers) i.mouseClicked(arg0);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		for(IInputListener i : inputUsers) i.mouseEntered(arg0);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		for(IInputListener i : inputUsers) i.mouseExited(arg0);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		for(IInputListener i : inputUsers) i.mousePressed(arg0);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		for(IInputListener i : inputUsers) i.mouseReleased(arg0);
	}

}
