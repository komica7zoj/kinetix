/**
 * 
 */
package blue.schema;

import tagaga.etl.utils.FileMeta;
import tagaga.etl.utils.TableSchemaParser;

/**
 * @author t
 *
 */
public class BlueScriptsForETLA {

  /**
   * 
   */
  public BlueScriptsForETLA() {
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    
    String folderPath = "C:\\OPM\\gitlab\\blueetl\\ref\\etla";
    
    FileMeta[] fileMetas = new FileMeta[] {
        
//        new FileMeta(folderPath+"/ETLA_Claim_Provision_Record.xlsx", true, false),
//        new FileMeta(folderPath+"/ETLA_Claim_Settlement_Record.xlsx", true, false),
//        new FileMeta(folderPath+"/ETLA_Commission_Provision_Record.xlsx", true, false),
//        new FileMeta(folderPath+"/ETLA_Commission_Settlement_Record.xlsx", true, false),
//        new FileMeta(folderPath+"/ETLA_Premium_Provision_Record.xlsx", true, false),
//        new FileMeta(folderPath+"/ETLA_Premium_Settlement_Record.xlsx", true, false),
//        new FileMeta(folderPath+"/ETLA_Oracle_invoice_record_for_settlement.xlsx", false, false),
//
//        new FileMeta(folderPath+"/ETLA_Reinsurance_Claim_Provision_Table.xlsx", false, true),
//        new FileMeta(folderPath+"/ETLA_Reinsurance_Premium_Provision_Table.xlsx", false, true),
//        new FileMeta(folderPath+"/ETLA_Reinsurance_Commission_Provision_Table.xlsx", false, true),
//
//        new FileMeta(folderPath+"/ETLA_Reinsurance_Claim_Settlement_Table.xlsx", false, true),
//        new FileMeta(folderPath+"/ETLA_Reinsurance_Premium_Settlement_Table.xlsx", false, true),
//        new FileMeta(folderPath+"/ETLA_Reinsurance_Commission_Settlement_Table.xlsx", false, true),
//
//        new FileMeta(folderPath+"/ETLA_Reinsurance_Claim_Receivable_Payable_Table.xlsx", false, true),
//        new FileMeta(folderPath+"/ETLA_Reinsurance_Premium_Receivable_Payable_Table.xlsx", false, true),
//        new FileMeta(folderPath+"/ETLA_Reinsurance_Commission_Receivable_Payable_Table.xlsx", false, true),
//
//        new FileMeta(folderPath+"/ETLA_Reinsurance_Claim_Paid_Table.xlsx", false, true),
//        new FileMeta(folderPath+"/ETLA_Reinsurance_Premium_Paid_Table.xlsx", false, true),
//        new FileMeta(folderPath+"/ETLA_Reinsurance_Commission_Paid_Table.xlsx", false, true),
//
//        new FileMeta(folderPath+"/ETLA_Reinsurance_Receivable_Payable_Table.xlsx", false, true),
        new FileMeta(folderPath+"/ETLA_Input_ClaimPrem_Incur_AS400.xlsx", true, false)
        
      };    
    
    
    TableSchemaParser schemaParser = new TableSchemaParser(fileMetas);
    
     String scripts = "";
     scripts += schemaParser.parse(new BlueParsePreference());
     scripts += schemaParser.parse(new BlueParsePreferenceForRaw());
      scripts += schemaParser.parse(new BlueParsePreferenceForTemp());
     
     System.out.println(scripts); 

  }

}
