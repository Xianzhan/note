/**
 * @author xianzhan
 * @since 2023-03-07
 */
module ui.swing {
    // fix: java.lang.module.FindException: Module lombok not found
    requires static lombok;

    requires java.desktop;
    requires org.slf4j;
    requires note.base;
}