/**
 * Copyright (C) 2012 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jbpm.formModeler.components.editor;

import org.jbpm.formModeler.service.bb.mvc.taglib.formatter.Formatter;
import org.jbpm.formModeler.service.bb.mvc.taglib.formatter.FormatterException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WysiwygMenuFormatter extends Formatter {
    private static transient org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(WysiwygMenuFormatter.class.getName());

    private WysiwygFormEditor editor;

    public WysiwygFormEditor getEditor() {
        return editor;
    }

    public void setEditor(WysiwygFormEditor editor) {
        this.editor = editor;
    }

    public void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws FormatterException {
        try {
            renderFragment("outputStart");

            setAttribute("formName", editor.getCurrentEditForm().getName());
            renderFragment("outputHeader");

            renderFragment("beforeOptions");

            renderFragment("optionsOutputStart");

            //<input type="image" onclick="setFormInputValue(this.form,'<factory:bean property="currentEditionOption"/>','<%=WysiwygFormEditor.EDITION_OPTION_FIELDTYPES%>');"
            //style="cursor:hand; margin-right: 15px;" title="Add Fields By Type" src="<static:image relativePath="general/AddFieldsByType.png"/>">

            String[] options = {WysiwygFormEditor.EDITION_OPTION_FIELDTYPES,
                    WysiwygFormEditor.EDITION_OPTION_FORM_PROPERTIES,
                    WysiwygFormEditor.EDITION_OPTION_BINDINGS_FIELDS,
                    WysiwygFormEditor.EDITION_OPTION_BINDINGS_SOURCES};

            String[] optionsImg = {WysiwygFormEditor.EDITION_OPTION_IMG_FIELDTYPES,
                    WysiwygFormEditor.EDITION_OPTION_IMG_FORM_PROPERTIES,
                    WysiwygFormEditor.EDITION_OPTION_IMG_BINDINGS_FIELDS,
                    WysiwygFormEditor.EDITION_OPTION_IMG_BINDINGS_SOURCES};

            String [] optionVis = {WysiwygFormEditor.EDITION_OPTION_VIS_MODE_FIELDTYPES,
                    WysiwygFormEditor.EDITION_OPTION_VIS_MODE_FORM_PROPERTIES,
                    WysiwygFormEditor.EDITION_OPTION_VIS_MODE_BINDINGS_FIELDS,
                    WysiwygFormEditor.EDITION_OPTION_VIS_MODE_BINDINGS_SOURCE};

            String render = "shared";
            for (int i = 0; i < options.length; i++) {
                String option = options[i];
                String optionImg = optionsImg[i];
                setAttribute("optionImage", optionImg);
                setAttribute("optionName", option);
                if (option.equals(editor.getCurrentEditionOption())){
                    render= optionVis[i];
                    renderFragment( "outputSelectedOption" );
                } else {
                    renderFragment("outputOption");
                }

            }

            setAttribute("renderMode", editor.getRenderMode());
            renderFragment("optionsOutputEnd");

            setAttribute("editionPage", "menu/" + editor.getCurrentEditionOption() + ".jsp");

            if("shared".equals(render) )
                renderFragment("outputWithFormEditionPage");
            else
                renderFragment("outputWithoutFormEditionPage");
            renderFragment("outputEnd");
        } catch (Exception e) {
            log.error("Error: ", e);
        }
    }
}
