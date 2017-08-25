package pyk.myapplication.api.model;

public abstract class Model {
  
  private final long rowId;
  
  public Model(long rowId) {
    this.rowId = rowId;
  }
  
  public long getRowId() {
    return rowId;
  }
}