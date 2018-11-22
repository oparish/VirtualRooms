package frontEnd;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import character.CharacterType;
import character.Character;
import main.Main;

public class RecruitList extends JFrame implements ListSelectionListener
{
	private static int HEIGHT = 500;	
	private static int WIDTH = 500;
	
	private JList<Character> list;
	private JTextArea infoPanel;
	
	private ArrayList<Character> characters;
	
	public RecruitList(ArrayList<Character> characters)
	{
		super();
		this.setLayout(new GridLayout(1, 2));
		this.characters = characters;
		this.setupList();
		this.setupInfoPanel();
		this.setSize(WIDTH, HEIGHT);
	}
	
	private void setupList()
	{
		Character[] characterArray = new Character[this.characters.size()];
		for (int i = 0; i < this.characters.size(); i++)
		{
			characterArray[i] = this.characters.get(i);
		}
		
		this.list = new JList<Character>(characterArray);
		this.list.addListSelectionListener(this);
		
		this.list.setCellRenderer(new RecruitListCellRenderer());
		
		this.add(this.list);
	}
	
	
	
	private void setupInfoPanel()
	{
		this.infoPanel = new JTextArea();
		this.add(this.infoPanel);
	}
	
	public static void main(String[] args)
	{
		Main.loadScenario();
		CharacterType characterType = Main.getCurrentScenario().getFirstCharacterType();
		ArrayList<Character> characters = new ArrayList<Character>();
		for (int i = 0; i < 10; i++)
		{
			characters.add(characterType.generateCharacter());
		}
		RecruitList recruitList = new RecruitList(characters);
		recruitList.addWindowListener(new ExitListener());
		Main.displayWindow(recruitList);
	}

	@Override
	public void valueChanged(ListSelectionEvent lse)
	{
		if (!lse.getValueIsAdjusting())
		{
			Character character = this.list.getSelectedValue();
			this.infoPanel.setText(character.toString());
		}
		
	}
	
	private class RecruitListCellRenderer implements ListCellRenderer<Character>
	{

		@Override
		public Component getListCellRendererComponent(JList<? extends Character> list, Character character, int index,
				boolean isSelected, boolean cellHasFocus) 
		{
			return new JLabel(character.getName());
		}
		
	}
}


