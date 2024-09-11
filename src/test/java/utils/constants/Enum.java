package utils.constants;

public class Enum {
    public enum MenuItems {
        ADMIN("Admin"), PIM("PIM"), LEAVE("Leave"), TIME("Time"), RECRUITMENT("Recruitment"), MY_INFO("My Info"), PERFORMANCE("Performance"), DASHBOARD("Dashboard"), DIRECTORY("Directory");
        private String menuItem;

        private MenuItems(final String menu) {
            this.menuItem = menuItem;
        }

        @Override
        public String toString() {
            return menuItem;
        }
    }
}
