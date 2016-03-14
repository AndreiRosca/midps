//---------------------------------------------------------------------------

#ifndef CounterInteractorH
#define CounterInteractorH
//---------------------------------------------------------------------------
#include <Classes.hpp>
#include <Controls.hpp>
#include <StdCtrls.hpp>
#include <Forms.hpp>
#include "sButton.hpp"
#include "sEdit.hpp"
#include "sLabel.hpp"
#include <ComCtrls.hpp>
//---------------------------------------------------------------------------
class TFirstTask : public TForm
{
__published:	// IDE-managed Components
        TsButton *upButton;
        TsButton *downButton;
        TsEdit *counterEditor;
        TsLabel *taskLabel;
        TsLabel *editorLabel;
        TsButton *exitButton;
        TUpDown *upDownControl;
        void __fastcall onUpButtonClick(TObject *Sender);
        void __fastcall onDownButtonClick(TObject *Sender);
        void __fastcall onExitButtonClick(TObject *Sender);
        void __fastcall onUpDownControlChange(TObject *Sender,
          bool &AllowChange, short NewValue, TUpDownDirection Direction);
private:	// User declarations
        int counter;
public:		// User declarations
        __fastcall TFirstTask(TComponent* Owner);
        void printCounter();
};
//---------------------------------------------------------------------------
extern PACKAGE TFirstTask *FirstTask;
//---------------------------------------------------------------------------
#endif
