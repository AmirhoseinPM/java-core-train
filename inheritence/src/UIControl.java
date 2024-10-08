public abstract class UIControl {
    private boolean isEnabled = true;

    // An abstract class do not have constructor
//    public UIControl(boolean isEnabled) {
//        this.isEnabled = isEnabled;
//    }

    public abstract void render();

    public void enable() {
        isEnabled = true;
    }
    public void disable() {
        isEnabled = false;
    }
    public boolean isEnabled() {
        return isEnabled;
    }

}
