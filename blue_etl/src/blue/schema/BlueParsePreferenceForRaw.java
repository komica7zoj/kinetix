/**
 * 
 */
package blue.schema;

import tagaga.etl.model.ColumnSchema;
import tagaga.etl.model.TableSchema;
import tagaga.etl.utils.AbstractParsePreference;
import tagaga.etl.utils.TableSchemaParser;

/**
 * @author t
 *
 */
public class BlueParsePreferenceForRaw extends AbstractParsePreference {

  /**
   * 
   */
  public BlueParsePreferenceForRaw() {

  }

  @Override
  public void applyPreference(TableSchema tableSchema) {
    
    tableSchema.setTableName(tableSchema.getTableName()+"_Raw");
    for (int i=0; i<tableSchema.getColumns().size(); i++) {
      ColumnSchema column = tableSchema.getColumns().get(i);
      
      if (ColumnSchema.Type.DATE.equals(column.getColumnType())) {
        column.setSyntax("YYYYMMDD");
        column.setExample("20210520");
      }
      
      column.setColumnType(ColumnSchema.Type.STRING);
      column.setPreferLength(1000);
      column.setAllowEmpty(true);
    } 

  }

  @Override
  public String getPostColumnScripts(TableSchema tableSchema) {
    
    return "\n\tCreated_By VARCHAR(50) NOT NULL DEFAULT ('SYSTEM'),"
        +"\n\tTime_Created DATETIME NOT NULL DEFAULT (GETDATE()),"
        +"\n\tID BIGINT IDENTITY(1,1),";
    
  }

  @Override
  public String getPostTableScripts(TableSchema tableSchema) {
    
    String tableName = tableSchema.getTableName();
    String viewNameForWrapper = "v_"+tableName+"_wrapper";
    String scripts = "";
    scripts += TableSchemaParser.generateDropViewScript(viewNameForWrapper);
    scripts += "\nGO\n";      
    scripts += "create view "+viewNameForWrapper+" as select "+tableSchema.getFieldNames()+" from "+tableName;
    scripts += "\nGO\n";
    
    return scripts;
    
  }


}
