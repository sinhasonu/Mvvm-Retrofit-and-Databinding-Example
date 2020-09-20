package com.sonu.karvytest;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;

import com.google.gson.annotations.SerializedName;
import com.sonu.karvytest.utils.CircleImageView;

import java.util.List;

public class EmployeeListModel extends BaseObservable implements Parcelable {

    /**
     * status : success
     * data : [{"id":"1","employee_name":"Tiger Nixon","employee_salary":"320800","employee_age":"61","profile_image":""},{"id":"2","employee_name":"Garrett Winters","employee_salary":"170750","employee_age":"63","profile_image":""},{"id":"3","employee_name":"Ashton Cox","employee_salary":"86000","employee_age":"66","profile_image":""},{"id":"4","employee_name":"Cedric Kelly","employee_salary":"433060","employee_age":"22","profile_image":""},{"id":"5","employee_name":"Airi Satou","employee_salary":"162700","employee_age":"33","profile_image":""},{"id":"6","employee_name":"Brielle Williamson","employee_salary":"372000","employee_age":"61","profile_image":""},{"id":"7","employee_name":"Herrod Chandler","employee_salary":"137500","employee_age":"59","profile_image":""},{"id":"8","employee_name":"Rhona Davidson","employee_salary":"327900","employee_age":"55","profile_image":""},{"id":"9","employee_name":"Colleen Hurst","employee_salary":"205500","employee_age":"39","profile_image":""},{"id":"10","employee_name":"Sonya Frost","employee_salary":"103600","employee_age":"23","profile_image":""},{"id":"11","employee_name":"Jena Gaines","employee_salary":"90560","employee_age":"30","profile_image":""},{"id":"12","employee_name":"Quinn Flynn","employee_salary":"342000","employee_age":"22","profile_image":""},{"id":"13","employee_name":"Charde Marshall","employee_salary":"470600","employee_age":"36","profile_image":""},{"id":"14","employee_name":"Haley Kennedy","employee_salary":"313500","employee_age":"43","profile_image":""},{"id":"15","employee_name":"Tatyana Fitzpatrick","employee_salary":"385750","employee_age":"19","profile_image":""},{"id":"16","employee_name":"Michael Silva","employee_salary":"198500","employee_age":"66","profile_image":""},{"id":"17","employee_name":"Paul Byrd","employee_salary":"725000","employee_age":"64","profile_image":""},{"id":"18","employee_name":"Gloria Little","employee_salary":"237500","employee_age":"59","profile_image":""},{"id":"19","employee_name":"Bradley Greer","employee_salary":"132000","employee_age":"41","profile_image":""},{"id":"20","employee_name":"Dai Rios","employee_salary":"217500","employee_age":"35","profile_image":""},{"id":"21","employee_name":"Jenette Caldwell","employee_salary":"345000","employee_age":"30","profile_image":""},{"id":"22","employee_name":"Yuri Berry","employee_salary":"675000","employee_age":"40","profile_image":""},{"id":"23","employee_name":"Caesar Vance","employee_salary":"106450","employee_age":"21","profile_image":""},{"id":"24","employee_name":"Doris Wilder","employee_salary":"85600","employee_age":"23","profile_image":""}]
     */

    @SerializedName("status")
    private String status;
    @SerializedName("data")
    private List<DataBean> data;

    protected EmployeeListModel(Parcel in) {
        status = in.readString();
        data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Creator<EmployeeListModel> CREATOR = new Creator<EmployeeListModel>() {
        @Override
        public EmployeeListModel createFromParcel(Parcel in) {
            return new EmployeeListModel(in);
        }

        @Override
        public EmployeeListModel[] newArray(int size) {
            return new EmployeeListModel[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeTypedList(data);
    }

    public static class DataBean extends BaseObservable implements Parcelable {
        /**
         * id : 1
         * employee_name : Tiger Nixon
         * employee_salary : 320800
         * employee_age : 61
         * profile_image :
         */

        @SerializedName("id")
        private String id;
        @SerializedName("employee_name")
        private String employeeName;
        @SerializedName("employee_salary")
        private String employeeSalary;
        @SerializedName("employee_age")
        private String employeeAge;
        @SerializedName("profile_image")
        private String profileImage;

        protected DataBean(Parcel in) {
            id = in.readString();
            employeeName = in.readString();
            employeeSalary = in.readString();
            employeeAge = in.readString();
            profileImage = in.readString();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEmployeeName() {
            return employeeName;
        }

        public void setEmployeeName(String employeeName) {
            this.employeeName = employeeName;
        }

        public String getEmployeeSalary() {
            return employeeSalary;
        }

        public void setEmployeeSalary(String employeeSalary) {
            this.employeeSalary = employeeSalary;
        }

        public String getEmployeeAge() {
            return employeeAge;
        }

        public void setEmployeeAge(String employeeAge) {
            this.employeeAge = employeeAge;
        }

        public String getProfileImage() {
            return profileImage;
        }

        public void setProfileImage(String profileImage) {
            this.profileImage = profileImage;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(employeeName);
            dest.writeString(employeeSalary);
            dest.writeString(employeeAge);
            dest.writeString(profileImage);
        }
    }
}
