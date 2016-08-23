package com.devfactory.drivingpartner.app.guidance;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by choiyejin on 16. 8. 23..
 */
public class DijkstraAlgorithm {

    double min=0;
    double sum_temp=100;//임시로 100을 지정 했지만 만약 비교 수 자체가 큰 수라면 변경 해야 함.
    int loc=0;
    List<Double> result= new ArrayList<>();
    List nearest (int n, double dis[]) //n= 목적지 갯수 , dis= 목적지간 거리값 array
    {


        //array List로 받았을 때 변환하는 코드 array List ->배열로 ////////////////////////////


        /////////////////////////////////////////////////////////////////////////////
        double sum=0;
        double resulmatrix[]=new double[n];
        double resultdis=0;
        int matrixsize =n;
        double seq[]=new double[matrixsize];
        double matrix[][]=new double[matrixsize][matrixsize];
        double temp[]=new double[matrixsize];
        int b=0;
        int c=1;
        int bignumber=100;// 100은 임의의 큰수 일뿐 변경 가능

        //////////////  자기 자신을 나타내는 거리값=0 초기화 부분////////////////////////////////////

        for (int i=0; i<matrixsize;i++)
        {
            for(int i2=0; i2<matrixsize;i2++)
            { matrix[i][i2]=bignumber;}
        }
        ///////////////  자기 자신을 나타내는 거리값=100(큰수) 초기화 부분////////////////////////////////////
        for(int des=1; des<n ;des++) // 도착지에 따른 iteration 즉 임의의 도착지 des에 따른 거리를 출력하기 위한부분
        {
            int flag=2;

            // sematrix 생성
            for (int i=0; i<n;i++)
            {
                matrix[i][0]=100;
                for (int i2=0; i2<n-c;i2++)
                {
                    matrix[i][i2+1+i]=dis[b];
                    matrix[i2+1+i][i]=matrix[i][i2+1+i];
                    b++;
                }
                c++;
            }
            //flag 초기화
            b=0;
            c=1;
            /////////////// 최종 목적지 des를 넣고 해당 값을 추출한다음 해당 열을 큰 수로 채우는 과정////////////////
            for(int i=0;i<n;i++)
            {
                temp[i]= matrix[i][des];
                temp[0]=bignumber;
                matrix[i][des]=bignumber;
            }
            ///////////// 최종 목적지로 부터 가장 가까운 목적지를 찾고 그 거기를 저장하고 다음 목적지로 옮겨가는 과정 ////////////////
            findMin(temp,n,min,loc);
            seq[n-flag]=loc;
            sum=sum+min;
            //////////// 최단거리 = [{시작점} {두번쩨}....... {두번째 마지막},{도착점}] 이라면 두번째 마지막 에서부터 시작점 까지의 거리를 구하는 부분 즉 도착점 에서 시작점으로 거리를 찾아 나감
            for(int i2=0; i2<n-3;i2++)//n개의 목적지에서 출발지+도착지+주번째 마지막 도착지를 이미 알고 있으므로 n-3개의 순서만 구하면 된다.
            {
                for(int i=0; i<n;i++)
                {
                    temp[i]=matrix[loc][i];
                    temp[0]=100;
                    matrix[i][loc]=1000;
                }
                findMin(temp,n,min,loc);
                sum=sum+min;
                flag=flag+1;
                seq[n-flag]=loc;
            }
            sum=sum+matrix[0][loc];//두번째 지점과 시작점의 거리를 마지막에 더해준다.
            seq[n-1]=des;
            ///////////////////////////////////////////////출력 및 값 비교 부분 /////////////////////////////////////////
            //seq = 최종 순서, sum 최단거리

            //이전 값과 현재 값을 비교하기 위해 temp 변수에 값 저장 하고 만약 값이 더 작으면 최신화

            if( sum_temp > sum )
            {
                result.clear();
                resultdis = sum;
                //resulmatrix = seq;
                for (int i=0;i<n; i++) {
                    result.add( seq[i]);
                }

            }
            //System.out.println(result);
            sum_temp=sum;
            ////변수 초기화///////////////////////////
            sum=0;
            min=0;
            loc=0;


        }
        System.out.println("따라서 최단 거리 순서는 "+result+"이며, 거리는"+resultdis+"입니다.");

        return result;
        //클래스로 거리랑 순서 둘다 보내면 좋겠지만....할줄 몰라요..
    }

    void findMin(double A[], double sizeval, double minval, double posval)
    {
        minval= A[0];
        for(int i = 0; i < sizeval; i++) // 08-21 수정 사항 =표시를 지워버림.. C에서 정상 작동하던 코드인데 이상하게..여기선 이게 걸림..이해 안감
        {
            if(minval > A[i])
            {
                min = A[i];
                loc = i;
            }
        }
    }
    //최소 값,자리 를 찾는 알고리즘 (정상 작동 확인)
}