package com.cognizant.truyum.dao;
import java.util.ArrayList;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.cognizant.truyum.exception.MenuItemNotFoundException;
import com.cognizant.truyum.model.MenuItem;

@Service
public class MenuItemDaoCollectionImpl implements MenuItemDao {

	private static ArrayList<MenuItem> menuItemList;
	static {
		ApplicationContext context = new ClassPathXmlApplicationContext("truyum.xml");
		menuItemList = (ArrayList<MenuItem>) context.getBean("menuItemList");
	}

	@Override
	public ArrayList<MenuItem> getMenuItemListCustomer() { // values are loaded from truyum.xml
		// TODO Auto-generated method stub

		return menuItemList;
	}

	@Override
	public MenuItem getMenuItem(long id) throws MenuItemNotFoundException {
		MenuItem mifnd = null;
		for (MenuItem mi : menuItemList) {
			if (mi.getId() == id) {
				mifnd = mi;
				break;
			}
		}
		if (mifnd == null)
			throw new MenuItemNotFoundException("Menu Item Not Found");
		else
			return mifnd;
	}

	@Override
	public void modifyMenuItem(MenuItem menuItem) throws MenuItemNotFoundException {
		int pos = 0;
		MenuItem mifnd = null;
		for (MenuItem mi : menuItemList) {
			if (mi.getId() == menuItem.getId()) {
				mifnd = mi;
				break;
			}
			pos++;
		}
		if (mifnd == null)
			throw new MenuItemNotFoundException("Menu Item with id : " + menuItem.getId() + "Not Found");
		else {
			menuItemList.set(pos, menuItem);
		}
	}

}
