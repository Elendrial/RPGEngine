package me.hii488.handlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import me.hii488.interfaces.IInputUser;

public class InputHandler implements MouseListener, KeyListener{

	public static InputHandler instance = new InputHandler();
	public static ArrayList<IInputUser> inputUsers = new ArrayList<IInputUser>();
	
	public static void addInputUser(IInputUser i){
		inputUsers.add(i);
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		ContainerHandler.getLoadedContainer().keyPressed(arg0);
		inputUsers.forEach(i -> i.keyPressed(arg0));
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		ContainerHandler.getLoadedContainer().keyReleased(arg0);
		inputUsers.forEach(i -> i.keyReleased(arg0));
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		ContainerHandler.getLoadedContainer().keyTyped(arg0);
		inputUsers.forEach(i -> i.keyTyped(arg0));
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		ContainerHandler.getLoadedContainer().mouseClicked(arg0);
		inputUsers.forEach(i -> i.mouseClicked(arg0));
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		ContainerHandler.getLoadedContainer().mouseEntered(arg0);
		inputUsers.forEach(i -> i.mouseEntered(arg0));
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		ContainerHandler.getLoadedContainer().mouseExited(arg0);
		inputUsers.forEach(i -> i.mouseExited(arg0));
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		ContainerHandler.getLoadedContainer().mousePressed(arg0);
		inputUsers.forEach(i -> i.mousePressed(arg0));
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		ContainerHandler.getLoadedContainer().mouseReleased(arg0);
		inputUsers.forEach(i -> i.mouseReleased(arg0));
	}

}
