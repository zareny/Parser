public class Document
  {

    private String content;

    public Document (String s)
      {
        content = s;
      }

    public Document ()
      {
        content = null;
      }

    public String getContent ()
      {
        return content;
      }

    public void setContent (String content)
      {
        this.content = content;
      }
  }
