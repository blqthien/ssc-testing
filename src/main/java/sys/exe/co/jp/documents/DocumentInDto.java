package sys.exe.co.jp.documents;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * ドキュメントDTO
 * 
 * @author quangthien.bienlam
 *
 */

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class DocumentInDto {

  /**
   * 論理名
   */
  private String logicalName;

  /**
   * ｶﾅ
   */
  private String kana;

  /**
   * 項タイプ
   */
  private String termType;

  /**
   * ドメイン
   */
  private String domain;

  /**
   * 画面項目名
   */
  private String webPageElementName;

  /**
   * 入力時コンポーネント
   */
  private String componentOnEntry;

  /**
   * 制限なし
   */
  private String noLimit;

  /**
   * 英数字
   */
  private String alphanumeric;

  /**
   * パスワード
   */
  private String password;

  /**
   * 数字
   */
  private String number;

  /**
   * 数字、‐
   */
  private String strNumber;

  /**
   * Eメール
   */
  private String eMail;

  /**
   * カナ大
   */
  private String kanaMajor;

  /**
   * 口座名義
   */
  private String accountHolder;

  /**
   * 最小
   */
  private String least;

  /**
   * 最大
   */
  private String max;

  /**
   * 物理名
   */
  private String physicsName;

  /**
   * 型
   */
  private String type;

  /**
   * 桁（文字数）
   */
  private double digitsLength;

  /**
   * セットアップ
   */
  private String setup;

  /**
   * シーケンス
   */
  private String sequence;

  /**
   * 項目補足
   */
  private String itemSupplement;

  /**
   * 現行型
   */
  private String currentType;

  /**
   * 現行桁（バイト数）
   */
  private double currentDigitsByteCount;

}
