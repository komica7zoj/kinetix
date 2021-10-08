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
public class BlueScriptsForETLD {

  /**
   * 
   */
  public BlueScriptsForETLD() {
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    
    String folderPath = "C:\\Users\\chun_chow\\OneDrive - Kinetix Systems Limited\\Documents\\ETLD\\ConvertToDB\\";
    
    FileMeta[] fileMetas = new FileMeta[] {
        
     // new FileMeta(folderPath+"ETLD_Input_GL_Tailor_made_report.xlsx", false, false),
     // new FileMeta(folderPath+"ETLD_Map_RI_Variable.xlsx", false, false),
     // new FileMeta(folderPath+"ETLD_Map_RI_Measurement.xlsx", false, false),
     // new FileMeta(folderPath+"ETLD_Map_AS400_LIS_Bank_Nominal_Code.xlsx", false, false),
     // new FileMeta(folderPath+"ETLD_Input_Actual_Cash_Flow_From_ETLA_RCH.xlsx", false, false),
     //   new FileMeta(folderPath+"ETLD_3_1_1_acf_je_posting.xlsx", false, false),
     //   new FileMeta(folderPath+"ETLD_3_1_2_bank_je_posting.xlsx", false, false),
     //   new FileMeta(folderPath+"ETLD_3_1_3_variable_measurement_je_posting.xlsx", false, false),
     //   new FileMeta(folderPath+"ETLD_3_1_4_aggregate_je_posting.xlsx", false, false),
     //   new FileMeta(folderPath+"ETLD_3_1_5_nominal_code_je_posting.xlsx", false, false),
     //   new FileMeta(folderPath+"ETLD_3_1_6_entity_code_je_posting.xlsx", false, false),
     //   new FileMeta(folderPath+"ETLD_3_1_7_policy_aggregate_je_posting.xlsx", false, false),
     //   new FileMeta(folderPath+"ETLD_3_1_8_Interfund_code_je_posting.xlsx", false, false),
     //   new FileMeta(folderPath+"ETLD_Output_RCH.xlsx", false, false),
     //   new FileMeta(folderPath+"ETLD_Output_tailor_make.xlsx", false, false),

    };

    TableSchemaParser schemaParser = new TableSchemaParser(fileMetas);
    
     String scripts = "";
     scripts += schemaParser.parse(new BlueParsePreference());
     scripts += schemaParser.parse(new BlueParsePreferenceForRaw());
     scripts += schemaParser.parse(new BlueParsePreferenceForTemp());
     System.out.println(scripts); 

  }

}
