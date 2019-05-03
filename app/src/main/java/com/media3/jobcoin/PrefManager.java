package com.media3.jobcoin;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class PrefManager {
    Context context;

    public PrefManager(Context context) {
        this.context = context;
    }


    public void saveUserData(String id, String name, String password, String email, String phone, String altph, String comtype, String hrqual,
                             String adres, String zipcode, String aadhar, String linkedIn, String facebook, String profile, String AadharImage, String file, String role,
                             String membershipid, String membership) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("HrProfileDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Id", id);
        editor.putString("Name", name);
        editor.putString("password", password);
        editor.putString("Email", email);
        editor.putString("Mobile", phone);
        editor.putString("altph", altph);
        editor.putString("comtype", comtype);
        editor.putString("qualification", hrqual);
        editor.putString("adres", adres);
        editor.putString("zipcode", zipcode);
        editor.putString("aadhar", aadhar);
        editor.putString("linkedIn", linkedIn);
        editor.putString("facebook", facebook);
        editor.putString("profile", profile);
        editor.putString("AadharImage", AadharImage);
        editor.putString("file", file);
        editor.putString("Role", role);
        editor.putString("MembershipId", membershipid);
        editor.putString("Membership", membership);
        Log.d(adres, "address");
        editor.commit();
    }

    public String getId() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("HrProfileDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Id", "");
    }

    public String gethrname() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("HrProfileDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Name", "");
    }

    public String gethrpass() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("HrProfileDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("password", "");
    }

    public String gethremil() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("HrProfileDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Email", "");
    }

    public String gethrmobile() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("HrProfileDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Mobile", "");
    }

    public String gethraltph() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("HrProfileDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("altph", "");
    }

    public String gethrcomtype() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("HrProfileDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("comtype", "");
    }

    public String gethrqual() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("HrProfileDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("qualification", "");
    }

    public String gethraddr() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("HrProfileDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("adres", "");
    }

    public String gethrzip() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("HrProfileDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("zipcode", "");
    }

    public String gethraadharno() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("HrProfileDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("aadhar", "");
    }

    public String gethrlink() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("HrProfileDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("linkedIn", "");
    }

    public String gethrface() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("HrProfileDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("facebook", "");
    }

    public String gethrppic() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("HrProfileDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("profile", "");
    }

    public String gethraadimg() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("HrProfileDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("AadharImage", "");
    }

    public String gethrresume() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("HrProfileDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("file", "");
    }

    public String gethrrole() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("HrProfileDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Role", "");
    }

    public String gethrmembershipid() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("HrProfileDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("MembershipId", "");
    }

    public String gethrmembershipname() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("HrProfileDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Membership", "");
    }
//JobSeeker Resume data start

    public void savejobseekerResume(String jfullname, String jdob, String jgender, String jMarital, String jHandicapped, String jfatherName, String jmotherName) {
        SharedPreferences sharedPrefjseeker = context.getSharedPreferences("JobSeekerResumeData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefjseeker.edit();
        editor.putString("jfullname", jfullname);
        editor.putString("jdob", jdob);
        editor.putString("jgender", jgender);
        editor.putString("jMarital", jMarital);
        editor.putString("jHandicapped", jHandicapped);
        editor.putString("jfatherName", jfatherName);
        editor.putString("jmotherName", jmotherName);
        editor.commit();
    }

    public String getjseekerresumename() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("JobSeekerResumeData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("jfullname", "");
    }

    public String getjseekerresumejdob() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("JobSeekerResumeData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("jdob", "");
    }

    public String getjseekerresumejgender() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("JobSeekerResumeData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("jgender", "");
    }

    public String getjseekerresumejmerital() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("JobSeekerResumeData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("jMarital", "");
    }

    public String getjseekerresumejHandicapped() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("JobSeekerResumeData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("jHandicapped", "");
    }

    public String getjseekerresumejfatherName() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("JobSeekerResumeData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("jfatherName", "");
    }

    public String getjseekerresumejmotherName() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("JobSeekerResumeData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("jmotherName", "");
    }

//JobSeeker Resume data end

    //jobseeker quallification start
    public void savejobseekerQualification(String jqualifiction, String jyearpass, String jgrade, String jpercentage, String jmediumofstudy) {
        SharedPreferences sharedPrefjseeker = context.getSharedPreferences("JobSeekerQualificationData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefjseeker.edit();
        editor.putString("jqualifiction", jqualifiction);
        editor.putString("jyearpass", jyearpass);
        editor.putString("jgrade", jgrade);
        editor.putString("jpercentage", jpercentage);
        editor.putString("jmediumofstudy", jmediumofstudy);
        editor.commit();

    }

    public String getjseekerqual() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("JobSeekerQualificationData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("jqualifiction", "");
    }

    public String getjseekerpassyear() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("JobSeekerQualificationData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("jyearpass", "");
    }

    public String getjseekergrade() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("JobSeekerQualificationData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("jgrade", "");
    }

    public String getjseekerpercentage() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("JobSeekerQualificationData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("jpercentage", "");
    }

    public String getjseekermediumofstudy() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("JobSeekerQualificationData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("jmediumofstudy", "");
    }

    //jobseeker quallification end

    //jobseeker certificate cources start

    public void savejobseekercertificatecourse(String jcertificatename, String jlookjobson, String jsub1, String jsub2) {
        SharedPreferences sharedPrefjseeker = context.getSharedPreferences("JobSeekerCertificateData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefjseeker.edit();
        editor.putString("jcertificatename", jcertificatename);
        editor.putString("jlookjobson", jlookjobson);
        editor.putString("jsub1", jsub1);
        editor.putString("jsub2", jsub2);
        editor.commit();

    }

    public String getjseekercertificate() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("JobSeekerCertificateData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("jcertificatename", "");
    }

    public String getjseekerjobson() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("JobSeekerCertificateData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("jlookjobson", "");
    }

    public String getjseekersub1() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("JobSeekerCertificateData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("jsub1", "");
    }

    public String getjseekersub2() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("JobSeekerCertificateData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("jsub2", "");
    }

    //end
//jobseeker experience and address Start
    public void savejobseekerexpandaddr(String jexperience, String jpermadd, String jpresadd, String jlocation) {
        SharedPreferences sharedPrefjseeker = context.getSharedPreferences("JobSeekerExperienceandAddData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefjseeker.edit();
        editor.putString("jexperience", jexperience);
        editor.putString("jpermadd", jpermadd);
        editor.putString("jpresadd", jpresadd);
        editor.putString("jlocation", jlocation);
        editor.commit();

    }

    public String getjseekerexperience() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("JobSeekerExperienceandAddData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("jexperience", "");
    }

    public String getjseekerpermadd() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("JobSeekerExperienceandAddData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("jpermadd", "");
    }

    public String getjseekerpreadd() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("JobSeekerExperienceandAddData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("jpresadd", "");
    }

    public String getjseekerlocation() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("JobSeekerExperienceandAddData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("jlocation", "");
    }
    //jobseeker experience and address End


    //jobseeker project and internship Start

    public void savejobseekerprojectintern(String jprojecttitle, String jprojectdesc, StringBuilder tel_checkbox,
                                           StringBuilder eng_checkbox, StringBuilder hind_checkbox, StringBuilder oria_checkbox) {
        SharedPreferences sharedPrefjseeker = context.getSharedPreferences("JobSeekerProjectData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefjseeker.edit();
        editor.putString("jprojecttitle", jprojecttitle);
        editor.putString("jprojectdesc", jprojectdesc);
        editor.putString("tel_checkbox", String.valueOf(tel_checkbox));
        editor.putString("eng_checkbox", String.valueOf(eng_checkbox));
        editor.putString("hind_checkbox", String.valueOf(hind_checkbox));
        editor.putString("oria_checkbox", String.valueOf(oria_checkbox));

        editor.commit();

    }

    public String getjseekerprojtitle() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("JobSeekerProjectData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("jprojecttitle", "");
    }

    public String getjseekerprojdesc() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("JobSeekerProjectData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("jprojectdesc", "");
    }

    public String getjseekerteluguvalues() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("JobSeekerTeluguData", Context.MODE_PRIVATE);
        //sharedPreferences.getString("tel_checkbox","");
        return sharedPreferences.getString("tel_checkbox", "");
    }

    public String getjseekerengvalues() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("JobSeekerEngData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("eng_checkbox", "");
    }

    public String getjseekerhindivalues() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("JobSeekerHindiData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("hind_checkbox", "");
    }

    public String getjseekeroriavalues() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("JobSeekerOriaData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("oria_checkbox", "");
    }
    //jobseeker project and internship End

    //jobseeker contact and resume start

    public void savejobseekerContactResume(String jemail, String jphone, String resumename) {
        SharedPreferences sharedPrefjseeker = context.getSharedPreferences("JobSeekerContactandResumeData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefjseeker.edit();
        editor.putString("jemail", jemail);
        editor.putString("jphone", jphone);
        editor.putString("resumename", resumename);
        editor.commit();

    }

    public String getjseekeremail() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("JobSeekerContactandResumeData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("jemail", "");
    }

    public String getjseekerphone() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("JobSeekerContactandResumeData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("jphone", "");
    }

    public String getjseekerresume() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("JobSeekerContactandResumeData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("resumename", "");
    }


    //id,fullname,email,phone,fathername,mothername,
    //                dob,permadd,presadd,location,genders,merriage,disablity,qualification,passyear,
    //                percentage,jobson,skill1,skill2,certifname,experience,protitle,prodesc,grade,
    //                et_resumename.getText().toString().trim(),mediumofstudy
    public void saveJobSeekerData(String jid, String jname, String jemail, String jphone, String jcomtype,
                                  String jqual, String jadres, String jzipcode, String jaadhar, String jlinkedIn, String jfacebook,
                                  String jprofile, String jAadharImage, String jfile, String jrole, String jfatherName, String jmotherName,
                                  String jdateOfBirth, String jage, String jGender, String jMarital, String jHandicapped, String jDisability,
                                  String jyearOfPassing, String jPercentage, String jSubject1, String jSubject2, String jSubject3, String jcourseName,
                                  String jcommunity, String jHigherPercentage, String jtotalexperience, String jTelugu,
                                  String jEnglish, String jHindi, String jOriya, String jprojectTitle, String jProjectDescription, String jgrade,
                                  String jpermenentAdress, String jlocation) {
        SharedPreferences sharedPrefjseeker = context.getSharedPreferences("JobSeekerResumeData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefjseeker.edit();
        editor.putString("Id", jid);
        editor.putString("Name", jname);

        editor.putString("Email", jemail);
        editor.putString("Mobile", jphone);

        editor.putString("comtype", jcomtype);
        editor.putString("qualification", jqual);
        editor.putString("adres", jadres);
        editor.putString("zipcode", jzipcode);
        editor.putString("aadhar", jaadhar);
        editor.putString("linkedIn", jlinkedIn);
        editor.putString("facebook", jfacebook);
        editor.putString("profile", jprofile);
        editor.putString("AadharImage", jAadharImage);
        editor.putString("file", jfile);
        editor.putString("Role", jrole);
        editor.putString("fathername", jfatherName);
        editor.putString("motherName", jmotherName);
        editor.putString("dateofbirth", jdateOfBirth);
        editor.putString("Age", jage);
        editor.putString("gender", jGender);
        editor.putString("merital", jMarital);
        editor.putString("handicaped", jHandicapped);
        editor.putString("disability", jDisability);
        editor.putString("gender", jGender);
        editor.putString("yearofpassing", jyearOfPassing);
        editor.putString("percentage", jPercentage);
        editor.putString("jSubject1", jSubject1);
        editor.putString("jSubject2", jSubject2);
        editor.putString("jSubject3", jSubject3);
        editor.putString("jcourseName", jcourseName);
        editor.putString("jcommunity", jcommunity);
        editor.putString("jHigherPercentage", jHigherPercentage);
        editor.putString("jtotalexperience", jtotalexperience);
        editor.putString("jTelugu", jTelugu);
        editor.putString("jEnglish", jEnglish);
        editor.putString("jHindi", jHindi);
        editor.putString("jOriya", jOriya);
        editor.putString("jprojectTitle", jprojectTitle);
        editor.putString("jProjectDescription", jProjectDescription);
        editor.putString("jgrade", jgrade);
        editor.putString("jpermenentAdress", jpermenentAdress);
        editor.putString("jlocation", jlocation);
        editor.commit();

    }

    public void saveTotalBronzeProfiles(String bronzesize) {
        Log.d(bronzesize, "shared bro size");
        SharedPreferences sharedPrefjseeker = context.getSharedPreferences("BronzeProfilecount", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefjseeker.edit();
        editor.putString("bronzesize", bronzesize);
        editor.commit();

    }

    public String getBronzeProfilecount() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("BronzeProfilecount", Context.MODE_PRIVATE);
        return sharedPreferences.getString("bronzesize", "");
    }

    public void saveTotalSilverProfiles(String silverzesize) {
        Log.d(silverzesize, "shared silver size");
        SharedPreferences sharedPrefjseeker = context.getSharedPreferences("SilverProfilecount", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefjseeker.edit();
        editor.putString("silversize", silverzesize);
        editor.commit();

    }

    public String getSilverProfilecount() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("SilverProfilecount", Context.MODE_PRIVATE);
        return sharedPreferences.getString("silversize", "");
    }

    public void saveTotalGoldProfiles(String goldsize) {
        Log.d(goldsize, "shared gold size");
        SharedPreferences sharedPrefjseeker = context.getSharedPreferences("GoldProfilecount", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefjseeker.edit();
        editor.putString("goldsize", goldsize);
        editor.commit();

    }

    public String getGoldProfilecount() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("GoldProfilecount", Context.MODE_PRIVATE);
        return sharedPreferences.getString("goldsize", "");
    }

    //Total platinum coin count

    public void saveTotalPlatinumProfiles(String platinumsize) {
        Log.d(platinumsize, "shared platinum size");
        SharedPreferences sharedPrefjseeker = context.getSharedPreferences("PlatinumProfilecount", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefjseeker.edit();
        editor.putString("platinumsize", platinumsize);
        editor.commit();

    }

    public String getPlatinumProfilecount() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PlatinumProfilecount", Context.MODE_PRIVATE);
        return sharedPreferences.getString("platinumsize", "");
    }


    public void saveImgurl(String imgurl, String imgname) {
        SharedPreferences sharedPrefjseeker = context.getSharedPreferences("ImgData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefjseeker.edit();
        editor.putString("imgurl", imgurl);
        Log.d(imgurl, "imgurl from premanager");
        editor.putString("imgname", imgname);
        editor.commit();
    }

    public String getImgurl() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("ImgData", Context.MODE_PRIVATE);
        Log.d("test", "in get imgurl");
        return sharedPreferences.getString("imgurl", "");
    }

    public String getImgname() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("ImgData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("imgname", "");
    }

    public void saveMembershipdetails(String membershipid, String membershipname, String membershipprice, String currentbalance, String totalamount, String productinfo) {
        SharedPreferences sharedPrefjseeker = context.getSharedPreferences("MembershipDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefjseeker.edit();
        editor.putString("membershipid", membershipid);
        editor.putString("membershipname", membershipname);
        editor.putString("membershipprice", membershipprice);
        editor.putString("currentbalance", currentbalance);
        editor.putString("totalamount", totalamount);
        editor.putString("productinfo", productinfo);
        editor.commit();

    }

    public String getmembershipid() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MembershipDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("membershipid", "");
    }

    public String getmembershipname() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MembershipDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("membershipname", "");
    }

    public String getmembershipprice() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MembershipDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("membershipprice", "");
    }

    public String getcurrentbalance() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MembershipDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("currentbalance", "");
    }

    public String gettotalamount() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MembershipDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("totalamount", "");
    }

    public String getproductinfo() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MembershipDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("prodinfo", "");
    }

    public void SavePaymentData(String seeker_id,String mkey, String txnid, String hash, String amount, String current_amount, String total_amount, String name,
                                String productinfo, String product_id, String mailid, String phoneno, String address, String membershipName,
                                String timestamp, String action, String surl, String furl, String cancel, String msg) {

        SharedPreferences sharedPrefjseeker = context.getSharedPreferences("PaymentData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefjseeker.edit();
        editor.putString("seeker_id", seeker_id);
        editor.putString("mkey", mkey);
        editor.putString("txnid", txnid);
        editor.putString("hash", hash);
        editor.putString("amount", amount);
        editor.putString("current_amount", current_amount);
        editor.putString("total_amount", total_amount);
        editor.putString("name", name);
        editor.putString("productinfo", productinfo);
        editor.putString("product_id", product_id);
        editor.putString("mailid", mailid);
        editor.putString("phoneno", phoneno);
        editor.putString("address", address);
        editor.putString("membershipName", membershipName);
        editor.putString("timestamp", timestamp);
        editor.putString("action", action);
        editor.putString("surl", surl);
        editor.putString("furl", furl);
        editor.putString("cancel", cancel);
        editor.putString("msg", msg);
        Log.d("message",msg);
        editor.commit();

    }
    public String getmkey()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PaymentData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("mkey","");
    }
    public String gettxnid()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PaymentData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("txnid","");
    }
    public String gethash()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PaymentData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("hash","");
    }
    public String getamount()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PaymentData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("amount","");
    }
    public String getcurrent_amount()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PaymentData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("current_amount","");
    }
    public String gettotal_amount()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PaymentData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("total_amount","");
    }
    public String getname()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PaymentData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("name","");
    }
    public String getpayproductinfo()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PaymentData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("productinfo","");
    }
    public String getproduct_id()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PaymentData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("product_id","");
    }
    public String getpaymailid()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PaymentData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("mailid","");
    }
    public String getpayphoneno()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PaymentData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("phoneno","");
    }
    public String getpayaddress()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PaymentData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("address","");
    }
    public String getpaymembershipName()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PaymentData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("membershipName","");
    }

    public String gettimestamp()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PaymentData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("timestamp","");
    }
    public String getaction()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PaymentData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("action","");
    }
    public String getsurl()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PaymentData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("surl","");
    }
    public String getfurl()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PaymentData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("furl","");
    }
    public String getcancel()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PaymentData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("cancel","");
    }
    public String getmsg()
    {

        SharedPreferences sharedPreferences = context.getSharedPreferences("PaymentData", Context.MODE_PRIVATE);
        Log.d("pre getmethod ",sharedPreferences.getString("msg",""));
        return sharedPreferences.getString("msg","");

    }
}
