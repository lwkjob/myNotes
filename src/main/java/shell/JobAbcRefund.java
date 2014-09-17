//package shell;
//public class JobAbcRefund
//{
//  private static AbcEnterpriseConn AEC = null;
//
//  public static void main(String[] args) {
//    Map paraMap = new HashMap();
//    paraMap.put("v_CCTransCode", "CQRA10");
//    paraMap.put("v_ProductID", "ICC");
//    paraMap.put("v_ChannelType", "ERP");
//    paraMap.put("v_CorpNo", "");
//    paraMap.put("v_OpNo", "");
//    paraMap.put("v_AuthNo", "");
//    paraMap.put("v_ReqSeqNo", "");
//    paraMap.put("v_ReqDate", DateUtil.toString("yyyyMMdd"));
//    paraMap.put("v_ReqTime", DateUtil.toString("HHmmss"));
//    paraMap.put("v_Sign", "");
//
//    paraMap.put("v_StartDate", DateUtil.toString("yyyyMMdd"));
//    paraMap.put("v_StartTime", "000001");
//    paraMap.put("v_EndDate", DateUtil.toString("yyyyMMdd"));
//    try {
//      AEC = new AbcEnterpriseConn();
//
//      Map resultMap = AEC.queryActDetailProcess(paraMap);
//
//      if ("1".equals(resultMap.get("v_FileFlag"))) {
//        String fileName = (String)resultMap.get("v_BatchFileName");
//        AEC.readFileByLines(fileName);
//      } else {
//        AEC.getLog().info("没有明细账文件");
//        System.out.println("没有明细账文件");
//      }
//    }
//    catch (Exception e) {
//      e.printStackTrace();
//      AEC.getLog().info("农行账户明细作业失败：" + e);
//      System.out.println("农行账户明细作业失败：" + e);
//    }
//  }
//}