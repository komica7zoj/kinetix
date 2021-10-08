/**
 * 
 */
package blue.schema;

import tagaga.etl.model.ColumnSchema;
import tagaga.etl.model.TableSchema;
import tagaga.etl.utils.IParsePreference;
import tagaga.etl.utils.TableSchemaParser;

/**
 * @author t
 *
 */
public class BlueParsePreference implements IParsePreference {

  /**
   * 
   */
  public BlueParsePreference() {
  }

  @Override
  public void applyPreference(TableSchema tableSchema) {
    
    int inDate = 0;
    int inValue = 0;
    
    for (int i=0; i<tableSchema.getColumns().size(); i++) {
      ColumnSchema column = tableSchema.getColumns().get(i);
      String columnName = column.getColumnName();
      if (columnName.toLowerCase().indexOf("date")!=-1) {
        column.setColumnType(ColumnSchema.Type.DATE);
      }else if (ColumnSchema.Type.STRING.equals(column.getColumnType())) {
        
        int maxLength = column.getMaxLength();
        if (maxLength*1.5 > 510) column.setPreferLength(800);
        else if (maxLength*1.5 > 255) column.setPreferLength(510);
        else if (maxLength == 0 || maxLength*1.5 > 100) column.setPreferLength(255);
        else if (maxLength*1.5 > 50) column.setPreferLength(100);
        else if (maxLength*1.5 > 20) column.setPreferLength(50);
        else if (maxLength*1.5 > 10) column.setPreferLength(20);
        else //if (maxLength > 3) 
          column.setPreferLength(10);
        
      }else if (column.getColumnType().equals(ColumnSchema.Type.DECIMAL)) {
        if (column.getColumnName().toLowerCase().indexOf("rate")!=-1) {
          column.setScale(5);
        }else {
          column.setPreferLength(13);
          column.setScale(2);
        }
        TableSchemaParser.rescaleExample(column);
      }

      if (column.getColumnName().toLowerCase().indexOf("amount")!=-1) {
        column.setColumnType(ColumnSchema.Type.DECIMAL);
        column.setPreferLength(13);
        column.setScale(2);       
      }
      
      if ("rider_plan_code".equals(column.getColumnName().toLowerCase())
          || "basic_plan_code".equals(column.getColumnName().toLowerCase())) {
        column.setMaxLength(10);
        column.setPreferLength(10);
      }        
      if ("Plan_Currency".equalsIgnoreCase(column.getColumnName())
          || "Transaction_Currency".equalsIgnoreCase(column.getColumnName())) {
        column.setMaxLength(3);
        column.setPreferLength(3);
      }     
      // date field would not be empty, user will use "NA" to represent NULL instead
      // double confirmed with J at 20210819 1700
      if (ColumnSchema.Type.DATE.equals(column.getColumnType())) {
          column.setAllowEmpty(true);
      }
      // reserve words handling //
      if ("date".equals(column.getColumnName().toLowerCase()))
        column.setColumnName("In_Date"+(++inDate));
      if ("value".equals(column.getColumnName().toLowerCase()))
        column.setColumnName("In_Value"+(++inValue));  
      
      /* TODO */
      column.setAllowEmpty(true);
    
    }      
  }

  @Override
  public String getConstraintsScripts(TableSchema tableSchema) {
    String tableName = tableSchema.getTableName();
    String scripts =  "alter table "+tableName+" add constraint FK_"+tableName+"__etl_job foreign key (Batch_No) references etl_job (batch_no) ON UPDATE CASCADE ON DELETE NO ACTION";
    scripts += "\nGO\n";
    return scripts;
  }

  @Override
  public String getPreColumnScripts(TableSchema tableSchema) {
    return "\n\tMonth_Stamp VARCHAR(6) NOT NULL,"
        + "\n\tBatch_No VARCHAR(20) NOT NULL,";
  }

  @Override
  public String getPostColumnScripts(TableSchema tableSchema) {
    return "\n\tCreated_By VARCHAR(50) NOT NULL DEFAULT ('SYSTEM'),"
        +"\n\tTime_Created DATETIME NOT NULL DEFAULT (GETDATE()),"
        +"\n\tModified_By VARCHAR(50) NOT NULL DEFAULT ('SYSTEM'),"
        +"\n\tLast_Modified DATETIME NOT NULL DEFAULT(GETDATE()),"
        +"\n\tID BIGINT IDENTITY(1,1),";
  }

  @Override
  public String getIndexScripts(TableSchema tableSchema) {
    
    String tableName = tableSchema.getTableName();
    String scripts = "";
    scripts += "CREATE INDEX IX_"+tableName+"__Month_Stamp ON "+tableName+"(Month_Stamp)";
    scripts += "\nGO\n";
    scripts += "CREATE INDEX IX_"+tableName+"__Batch_No ON "+tableName+"(Batch_No)";
    scripts += "\nGO\n";
    return scripts;
  }

  @Override
  public String getPostTableScripts(TableSchema tableSchema) {
    if (tableSchema.isWorkingTable())
      return "exec sp_Register_Interface_File '"+tableSchema.getTableName()+"'\n";
    return "";
  }

}
