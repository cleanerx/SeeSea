/**
 * 
 Copyright (c) 2010-2012, Jens K�bler All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer. Redistributions in binary
 * form must reproduce the above copyright notice, this list of conditions and
 * the following disclaimer in the documentation and/or other materials provided
 * with the distribution. Neither the name of the <organization> nor the names
 * of its contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */

package net.sf.seesea.nmea.rcp.application;

import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ContributionItemFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	private final IWorkbenchWindow _window;

//    private IWorkbenchAction introAction;
    
    protected IWorkbenchAction closeAction;

    protected IWorkbenchAction closeAllAction;

    protected IWorkbenchAction saveAction;
    
    protected IWorkbenchAction deleteAction;

    private IWorkbenchAction undoAction;

    private IWorkbenchAction redoAction;
    
    protected IWorkbenchAction copyAction;

    protected IWorkbenchAction cutAction;

    protected IWorkbenchAction pasteAction;

    protected IWorkbenchAction selectAllAction;

    protected IWorkbenchAction newEditorAction;

    protected IWorkbenchAction openPreferencesAction;

    protected IAction savePerspectiveAction;

    protected IAction resetPerspectiveAction;

    protected IAction closePerspAction;

    protected IAction closeAllPerspsAction;

    protected IWorkbenchAction quitAction;

	private IWorkbenchAction _helpAction;

	private IWorkbenchAction _aboutAction;

	private IWorkbenchAction showViewMenuAction;
    
	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	    _window = configurer.getWindowConfigurer().getWindow();
	}

	@Override
	protected void makeActions(IWorkbenchWindow window) {
		super.makeActions(window);
//		introAction = ActionFactory.INTRO.create(window);
//		register(introAction);
	    _aboutAction = ActionFactory.ABOUT.create(window);
//	    _aboutAction.setImageDescriptor(_imageAbout);
	    register(_aboutAction);
	    _helpAction = ActionFactory.HELP_CONTENTS.create(window);
	    register(_helpAction);
//        showViewMenuAction = ActionFactory.SHOW_VIEW_MENU.create(window);
//        register(showViewMenuAction);
	    
//	    _showLicenseAction = new ShowLicenseAction(window);
//	    register(_showLicenseAction);

		
	    quitAction = ActionFactory.QUIT.create(window);
	    register(quitAction);

	    openPreferencesAction = ActionFactory.PREFERENCES.create(window);
	    register(openPreferencesAction);
	}

	@Override
	protected void fillMenuBar(IMenuManager menuBar) {
	    menuBar.add(createFileMenu());
//	    menuBar.add(createEditMenu());
		
	    menuBar.add(createWindowMenu());
	    menuBar.add(createHelpMenu());

	    
//		MenuManager helpMenu = new MenuManager("&Help", IWorkbenchActionConstants.M_HELP);
//		menuBar.add(helpMenu);

		// Help
//		helpMenu.add(introAction);
	}

	  private MenuManager createHelpMenu() {
		  MenuManager helpMenu = new MenuManager("Help", IWorkbenchActionConstants.M_HELP); //$NON-NLS-1$		    helpMenu.add(_aboutAction);
		  helpMenu.add(_aboutAction);
		  helpMenu.add(_helpAction);
//		    helpMenu.add(_createBugReportAction);
		return helpMenu;
	}

	protected MenuManager createFileMenu() {
		    MenuManager menu = new MenuManager(Messages.getString("ApplicationActionBarAdvisor.file"), IWorkbenchActionConstants.M_FILE); //$NON-NLS-1$
//		    menu.add(new GroupMarker(IWorkbenchActionConstants.FILE_START));
//		    {
//	            // create the New submenu, using the same id for it as the New action
//		    	String newText = "New";
//	            String newId = ActionFactory.NEW.getId();
//	            MenuManager newMenu = new MenuManager(newText, newId);
//	            newMenu.setActionDefinitionId("org.eclipse.ui.file.newQuickMenu"); //$NON-NLS-1$
//	            newMenu.add(new Separator(newId));
////	            this.newWizardMenu = new NewWizardMenu(getWindow());
////	            newMenu.add(this.newWizardMenu);
//	            newMenu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
//	            menu.add(newMenu);
//		    }

//		    menu.add(new GroupMarker(IWorkbenchActionConstants.NEW_EXT));
//		    menu.add(new Separator());

//		    menu.add(closeAction);
//		    menu.add(closeAllAction);
		    // menu.add(closeAllSavedAction);
//		    menu.add(new GroupMarker(IWorkbenchActionConstants.CLOSE_EXT));
//		    menu.add(new Separator());
//		    menu.add(saveAction);
//		    menu.add(new Separator());
		    // TODO: implement refresh action...
		    // menu.add(refreshAction);

//		    menu.add(new GroupMarker(IWorkbenchActionConstants.SAVE_EXT));
//		    menu.add(new Separator());
//		    menu.add(new GroupMarker(IWorkbenchActionConstants.PRINT_EXT));
//		    menu.add(new Separator());
//		    menu.add(new GroupMarker(IWorkbenchActionConstants.OPEN_EXT));
//		    menu.add(new Separator());
//		    menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
//
//		    menu.add(new GroupMarker(IWorkbenchActionConstants.MRU));
//		    menu.add(new Separator());
		    menu.add(new GroupMarker(IWorkbenchActionConstants.FILE_END));
		    menu.add(quitAction);
		    return menu;
		  }

		  protected MenuManager createEditMenu() {
		    MenuManager menu = new MenuManager("Edit", IWorkbenchActionConstants.M_EDIT); //$NON-NLS-1$
	        menu.add(undoAction);
	        menu.add(redoAction);
	        menu.add(new GroupMarker(IWorkbenchActionConstants.UNDO_EXT));
	        menu.add(new Separator());

		    menu.add(selectAllAction);
		    menu.add(cutAction);
		    menu.add(copyAction);
		    menu.add(pasteAction);
		    menu.add(deleteAction);
		    return menu;
		  }
	
	@Override
	public void dispose() {
		super.dispose();
	    newEditorAction = null;
//	    introAction = null;
	    openPreferencesAction = null;
	    savePerspectiveAction = null;
	    resetPerspectiveAction = null;
	    closePerspAction = null;
	    closeAllPerspsAction = null;
	    closeAction = null;
	    closeAllAction = null;
	    saveAction = null;
	    quitAction = null;
	    deleteAction = null;
	    copyAction = null;
	    cutAction = null;
	    pasteAction = null;
	    selectAllAction = null;
	    _aboutAction = null;
	    _helpAction = null;

	}

	  /**
	   * Creates and returns the Window menu.
	   */
	  protected MenuManager createWindowMenu() {
	    MenuManager menu = new MenuManager("Window", IWorkbenchActionConstants.M_WINDOW); //$NON-NLS-1$
//	    menu.add(newEditorAction);
//	    menu.add(new Separator());
//	    addPerspectiveActions(menu);
//	    menu.add(new Separator());
	    // addKeyboardShortcuts(menu);

	    //	    menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
//	    menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS + "end")); //$NON-NLS-1$
//	    menu.add(showViewMenuAction);
	    menu.add(openPreferencesAction);

	    return menu;
	  }

	
	  protected void addPerspectiveActions(MenuManager menu) {
		    addOpenPerspectiveActions(menu);
		    menu.add(new Separator());
//		    menu.add(savePerspectiveAction);
		    menu.add(resetPerspectiveAction);
//		    menu.add(closePerspAction);
//		    menu.add(closeAllPerspsAction);
		  }

	  
	  protected void addOpenPerspectiveActions(MenuManager menu) {
		    String openText = ""; //$NON-NLS-1$
		    MenuManager changePerspMenuMgr = new MenuManager(openText, Messages.getString("ApplicationActionBarAdvisor.perspective")); //$NON-NLS-1$
		    IContributionItem changePerspMenuItem = ContributionItemFactory.PERSPECTIVES_SHORTLIST.create(_window);
		    changePerspMenuMgr.add(changePerspMenuItem);
		    menu.add(changePerspMenuMgr);
		  }


}
