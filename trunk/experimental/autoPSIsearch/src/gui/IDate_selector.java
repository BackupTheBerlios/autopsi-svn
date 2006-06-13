package gui;

import java.awt.event.ActionListener;
import java.util.Date;

public interface IDate_selector {
	public static final int CHANGE_ACTION = 0;
    public static final int SELECT_ACTION = 1;

    public void addActionListener(ActionListener l);
    public void removeActionListener(ActionListener l);

    public Date get_selected_date();
    public Date get_current_date();

    /** Must work just like {@link Calendar#roll(int,boolean)} */
    public void roll(int flag, boolean up);

    /** Must work just like {@link Calendar#get(int)} */
    public int get(int flag);
}
