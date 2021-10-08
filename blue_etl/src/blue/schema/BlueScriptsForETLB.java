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
public class BlueScriptsForETLB {

  /**
   * 
   */
  public BlueScriptsForETLB() {
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    
    String folderPath = "C:\\Users\\chun_chow\\OneDrive - Kinetix Systems Limited\\Documents\\ETLB\\Convert2DB\\";
    
    FileMeta[] fileMetas = new FileMeta[] {
        
      //new FileMeta(folderPath+"/ETLB_Aggregation_Output.xlsx", false, false),
      //new FileMeta(folderPath+"/ETLB_Closing_JE_RI.xlsx", false, false), 
      //new FileMeta(folderPath+"/ETLB_JE_RI.xlsx", false, false),
      //new FileMeta(folderPath+"ETLB_List_of_vars_for_exclusion.xlsx", false, false),
      //new FileMeta(folderPath+"ETLB_List_of_vars_with_calc_step_for_exclusion.xlsx", false, false),
      //new FileMeta(folderPath+"/ETLB_Mapping table_CoA_ICA_RCL.xlsx", false, false),
      //new FileMeta(folderPath+"ETLB_Mappingentity_hiera.xlsx", false, false),
      //new FileMeta(folderPath+"/ETLB_Reclassification_Output.xlsx", false, false),
    //new FileMeta(folderPath+"ETLB_Input_Closing_JE_RI.xlsx", false, false),
    //new FileMeta(folderPath+"ETLB_List_of_CoA_reclassify.xlsx", false, false),
    //new FileMeta(folderPath+"ETLB_Mapping_table_CoA_ICA_RCL.xlsx", false, false),
    		//new FileMeta(folderPath+"ETLB_1_1_Aggregation_Vars_WKTB.xlsx", false, false),
    		//new FileMeta(folderPath+"ETLB_1_2_Aggregation_Cal_Vars_WKTB.xlsx", false, false),
    		//new FileMeta(folderPath+"ETLB_1_3_Aggregation_Map_WKTB.xlsx", false, false),
    		//new FileMeta(folderPath+"ETLB_Output_Aggregation.xlsx", false, false),
    		//new FileMeta(folderPath+"ETLB_List_of_CoA.xlsx", false, false),
    		//new FileMeta(folderPath+"RI_Profolio_lookup.xlsx", false, false),
    		new FileMeta(folderPath+"ETLB_2_1_Reclassification_GroupLevel_WKTB.xlsx", false, false),
    		new FileMeta(folderPath+"ETLB_2_2_Reclassification_List_WKTB.xlsx", false, false),
    		//new FileMeta(folderPath+"ETLB_2_3_Reclassification_Mapping_WKTB.xlsx", false, false),
    };

    TableSchemaParser schemaParser = new TableSchemaParser(fileMetas);
    
     String scripts = "";
     scripts += schemaParser.parse(new BlueParsePreference());
     scripts += schemaParser.parse(new BlueParsePreferenceForRaw());
     scripts += schemaParser.parse(new BlueParsePreferenceForTemp());
     
     System.out.println(scripts); 

  }

}
