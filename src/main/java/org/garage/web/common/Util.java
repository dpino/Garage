/*
 * This file is part of Garage
 *
 * Copyright (C) 2011 Igalia, S.L.
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

package org.garage.web.common;
import org.zkoss.zk.ui.Component;
import org.zkoss.zkplus.databind.DataBinder;

/**
 *
 * @author Jesus Miguel Sayar Celestino <xuxoceleste@gmail.com>
 * @author Diego Pino García <dpino@igalia.com>
 *
 */
public class Util {

    public static void reloadBindings(Component... toReload) {
        for (Component reload : toReload) {
            DataBinder binder = Util.getBinder(reload);
            if (binder != null) {
                binder.loadComponent(reload);
            }
        }
    }

    public static DataBinder getBinder(Component component) {
        return (DataBinder) component.getVariable("binder", false);
    }

}
