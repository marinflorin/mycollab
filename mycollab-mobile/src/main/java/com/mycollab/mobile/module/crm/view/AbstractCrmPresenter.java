/**
 * Copyright © MyCollab
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.mobile.module.crm.view;

import com.mycollab.core.MyCollabException;
import com.mycollab.mobile.mvp.AbstractPresenter;
import com.mycollab.vaadin.mvp.PageView;
import com.mycollab.vaadin.mvp.PresenterResolver;
import com.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.ui.HasComponents;

/**
 * @author MyCollab Ltd
 * @since 5.2.5
 */
public class AbstractCrmPresenter<V extends PageView> extends AbstractPresenter<V> {
    public AbstractCrmPresenter(Class<V> viewClass) {
        super(viewClass);
    }

    @Override
    protected void onGo(HasComponents navigator, ScreenData<?> data) {
        if (navigator instanceof NavigationManager) {
            NavigationManager navManager = ((NavigationManager) navigator);
            navManager.navigateTo(getView());
            if (navManager.getPreviousComponent() == null) {
                AllActivitiesPresenter activitiesPresenter = PresenterResolver.getPresenter(AllActivitiesPresenter.class);
                navManager.setPreviousComponent(activitiesPresenter.getView());
            }
        } else {
            throw new MyCollabException("Invalid flow");
        }
    }
}
