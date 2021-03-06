package cn.yiiguxing.plugin.translate.ui.settings

import cn.yiiguxing.plugin.translate.GoogleTranslateSettings
import cn.yiiguxing.plugin.translate.trans.GoogleTranslator
import cn.yiiguxing.plugin.translate.ui.LanguageRenderer
import cn.yiiguxing.plugin.translate.ui.form.GoogleTranslateSettingsForm
import cn.yiiguxing.plugin.translate.ui.selected
import com.intellij.ui.CollectionComboBoxModel
import javax.swing.Icon
import javax.swing.JComponent

/**
 * GoogleTranslateSettingsPanel
 *
 * Created by Yii.Guxing on 2018/1/18
 */
class GoogleTranslateSettingsPanel(
        private val settings: GoogleTranslateSettings
) : GoogleTranslateSettingsForm(), TranslatorSettingsPanel {

    override val id: String = GoogleTranslator.id
    override val name: String = "Google翻译"
    override val icon: Icon = GoogleTranslator.icon
    override val component: JComponent = contentPanel

    init {
        primaryLanguage.apply {
            model = CollectionComboBoxModel(GoogleTranslator.supportedTargetLanguages)
            setRenderer(LanguageRenderer)
        }
    }

    override val isModified: Boolean
        get() = primaryLanguage.selectedItem != settings.primaryLanguage

    override fun reset() {
        settings.primaryLanguage.let {
            if (GoogleTranslator.supportedTargetLanguages.contains(it)) {
                primaryLanguage.selectedItem = it
            }
        }
    }

    override fun apply() {
        primaryLanguage.selected?.let {
            settings.primaryLanguage = it
        }
    }
}