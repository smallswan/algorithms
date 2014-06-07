package org.gxu.algorithm.common;

import java.math.BigInteger;


/**
 * 水仙花数：N位整数，它等于各位上的数字的N次方之和，例如有一个N位数字，a1a2a3a4.....aN = a1^N +a2^N+......aN^N
 * 
 * 算法原理： 注意：以下的 sum 为各个位上的数字的N次方之和 sum_a为集合a中的数的sum
 * 
 * 对于任意的N位数字，定义形如315，351，513，531,135,153等这样的数字都属于“1出现1次，3出现1次，5出现1次”的集合a
 * 明显可以看出“包含在集合a中的数的sum都相同”，即sum_a="1^N（位数）*T1(1出现的次数)+3^N*T3(3出现的次数)+5^N*T5(5出现的次数)"，
 * 观察得，如果集合a包含水仙花数，则该水仙花数=水仙花数的sum（水仙花数定义）=sum_a（充要条件）。
 * 可以随便举个反例215,512，125在集合b中，但b的sum_a=134明显不属于集合b，所以b不是包含水仙花数的集合
 * 总结：将寻找水仙花数，转换为寻找包含水仙花数的集合，从而减少判断的次数。
 * 
 * 结束条件：（楼主在这里进行了优化） 首先不是一次选完，而是从0到N个9，0到N个8...这样选，总数由remainCount控制 设当前情况为集合a
 * 1.如果当sum_a大于最大数即10^N-1，矛盾 ;  确定集合a的上限
 * 
 * 2.因最小的数字为10^(N -1)，注意到，如果选某数时sum_a小于最小值，则后面的情况不用讨论了。;确定集合a的下限
 * 例如3位数，已选1个3，接着选2，发现sum_a最大为=3^3*1+2^3*2=43<100，可以断定不用选2,1,0等小于3,2,2组合的情况了；
 * （当前情况能表示的最大数：比如说3位数我选了1个9，8的情况选完了不行，现在开始选7，最大数就是977不可能是987(与前提矛盾)）
 * 
 * 3.判断sum_a和当前情况能表示的最大数首部相同部分中某数出现的次数是否比已经选择的集合中该数出现的次数多
 * 设sum_a=99900,当前情况能表示的最大数为99988，则当前情况的数肯定在这之间,即999XX，而当前情况9已经选了且只选了1次，则矛盾。
 * 
 * 4.同上：相同部分中还没选的数 的出现次数比剩余的总数还多 例如相同部分为789111,1还没选而且只剩2个数没选了，则矛盾
 * 
 * 5.当选完所有数时,如果sum_a属于集合a，则sum_a为水仙花数。
 * 
 */
public class NarcissusNumber {
    /**
     * 记录10的0~N次方
     */
    private static BigInteger[] PowerOf10;
    /**
     * 记录0到9中任意数字i的N次方,以及其乘以i出现的次数j的结果（i^N*j），避免使用时重复计算
     */
    private static BigInteger[][] PreTable;
    
    /**
     * 记录可能为水仙花数的下限与PreTable中对应数的差
     */
    // private static BigInteger[][] PreTable2; 没什么用，变量定多了不容易理解
    
    /**
     * 记录离PreTable中对应数最近的10的k次方
     */
    private static int[][] PreTable3;
    /**
     * 记录0到9中每个数出现的次数
     */
    private static int[] Selected = new int[10];
    /**
     * 记录水仙花数的位数
     */
    private static int Length;
    /**
     * 记录水仙花数出现的个数
     */
    private static int Count = 0;
    /**
     * 记录当前的进制
     */
    private static int NumberSystem = 10;
    
    
    
    //NarcissusNumber的构造函数
	
    public NarcissusNumber(){
    	
    }
    // 初始化计算时使用的数据结构，这也是提高效率的地方
    /**
     * @param n
     *            水仙花数的位数
     */
   
    public NarcissusNumber(int n) {
        PowerOf10 = new BigInteger[n + 1];
        PowerOf10[0] = BigInteger.ONE;//BigInteger.ONE为BigInteger 的常量 1。
        Length = n;
        
        //初始化PowerOf10数组
        for (int i = 1; i <= n; i++){
            PowerOf10[i] = PowerOf10[i - 1].multiply(BigInteger.TEN);
        }
    
        PreTable = new BigInteger[NumberSystem][n + 1];
        // PreTable2 = new BigInteger[NumberSystem][n + 1];
        PreTable3 = new int[NumberSystem][n + 1];
        
        //PreTable[i][j] 为0-i的N次方出现0-j次的值,即i^N*j的值
        for (int i = 0; i < NumberSystem; i++) {
            for (int j = 0; j <= n; j++) {
                PreTable[i][j] = new BigInteger(new Integer(i).toString()).pow(
                        n).multiply(new BigInteger(new Integer(j).toString()));
                // PreTable2[i][j] = PowerOf10[Length - 1]
                // .subtract(PreTable[i][j]);
                
                //记录小于PreTable[i][j]的PowerOf10[k]最大k值
                for (int k = n; k >= 0; k--) {
                    if (PowerOf10[k].compareTo(PreTable[i][j]) < 0) {
                        PreTable3[i][j] = k;
                        break;
                    }
                }
            }
        }
    }//public NarcissusNumber(int n)

    //显示水仙花数
    public void show() {
        Search(NumberSystem - 1, BigInteger.ZERO, Length);
    }

    /**
     * @param currentIndex
     *            记录当前正在选择的数字(0~9)
     * @param sum
     *            记录当前值（如选了3个9、2个8 就是9^N*3+8^N*2）
     * @param remainCount
     *            记录还可选择多少数
     */
    //在集合中递归地寻找水仙花数,并将结果输出，这是本程序中最重要的方法，也是最难的方法！
    private static void Search(int currentIndex, BigInteger sum, int remainCount) {
        if (sum.compareTo(PowerOf10[Length]) >= 0)// 见结束条件1 
        {
            return;
        }
    
        if (remainCount == 0) {// 没数可选时
            if (sum.compareTo(PowerOf10[Length - 1]) > 0 && Check(sum)) {// 见结束条件5
                Count++;//水仙花数个数
                System.out.print(Count + " ");//输出已经找到的水仙花数的个数
                System.out.println(sum);      //输出水仙花数
            }
            return;
        }
    
        if (!PreCheck(currentIndex, sum, remainCount))// 见结束条件3,4
            return;
    
        if (sum.add(PreTable[currentIndex][remainCount]).compareTo(
                PowerOf10[Length - 1]) < 0)// 见结束条件2
            return;
    
        if (currentIndex == 0) {// 选到0这个数时的处理
            Selected[0] = remainCount;
            Search(-1, sum, 0);
        } else {
            for (int i = 0; i <= remainCount; i++) {// 穷举所选数可能出现的情况
                Selected[currentIndex] = i;
                Search(currentIndex - 1, sum.add(PreTable[currentIndex][i]),
                        remainCount - i);
            }
        }
        // 到这里说明所选数currentIndex的所有情况都遍历了
        Selected[currentIndex] = 0;
    }

    /**
     * @param currentIndex
     *            记录当前正在选择的数字(0~9)
     * @param sum
     *            记录当前值（如选了3个9、2个8 就是9^N*3+8^N*2）
     * @param remainCount
     *            记录还可选择多少数
     * @return 如果当前值符合条件返回true
     */
    private static Boolean PreCheck(int currentIndex, BigInteger sum,
            int remainCount) {
        if (sum.compareTo(PreTable[currentIndex][remainCount]) < 0)//判断当前值是否小于PreTable中对应元素的值
            return true;// 说明还有很多数没选
    
        BigInteger max = sum.add(PreTable[currentIndex][remainCount]);// 当前情况的最大值
        max = max.divide(PowerOf10[PreTable3[currentIndex][remainCount]]);// 取前面一部分比较
        sum = sum.divide(PowerOf10[PreTable3[currentIndex][remainCount]]);//
    
        while (!max.equals(sum)) {// 检验sum和max首部是否有相同的部分
            max = max.divide(BigInteger.TEN);
            sum = sum.divide(BigInteger.TEN);
        }
    
        if (max.equals(BigInteger.ZERO))// 无相同部分
            return true;
    
        int[] counter = GetCounter(max);
    
        for (int i = 9; i > currentIndex; i--)
            if (counter[i] > Selected[i])// 见结束条件3
                return false;
    
        for (int i = 0; i <= currentIndex; i++)
            remainCount -= counter[i];
    
        return remainCount >= 0;// 见结束条件4
    }

    /**
     * 检查sum是否是花朵数
     * @param sum
     *            记录当前值（如选了3个9、2个8 就是9^N*3+8^N*2）
     * @return 如果sum存在于所选集合中返回true
     */
    private static Boolean Check(BigInteger sum) {
        int[] counter = GetCounter(sum);
    
        for (int i = 0; i < NumberSystem; i++) {
            if (Selected[i] != counter[i])
                return false;
        }   
        return true;
    }

    /**
     * @param value
     *            需要检验的数
     * @return 返回value中0到9出现的次数的集合
     */
    public static int[] GetCounter(BigInteger value) {
        int[] counter = new int[NumberSystem];
        char[] sumChar = value.toString().toCharArray();//将需要检验的数转变为字符串数组，便于统计各个数字出现的次数

        for (int i = 0; i < sumChar.length; i++)
            counter[sumChar[i] - '0']++;//

        return counter;//返回value中0到9出现的次数的集合
    }
}