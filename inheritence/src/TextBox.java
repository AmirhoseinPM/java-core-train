public class TextBox extends UIControl{
    private String text = "";

    public TextBox(String text) {
//        super(isEnabled);
        this.text = text;
    }


    @Override
    public void render() {
        System.out.println("render text box");
    }

    @Override
    public String toString() {
        return text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
